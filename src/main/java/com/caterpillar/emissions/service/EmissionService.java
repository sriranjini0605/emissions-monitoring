package com.caterpillar.emissions.service;

import com.caterpillar.emissions.model.EmissionData;
import com.caterpillar.emissions.repository.EmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Component
public class EmissionService {

    private final EmissionRepository repository;
    private final SnsClient snsClient;

    private static final String TOPIC_ARN = "YOUR_SNS_TOPIC_ARN";

    public EmissionData processEmissionData(EmissionData data) {
        boolean alert = data.getCarbonMonoxide() > 50 ||
                data.getNitrogenOxides() > 40 ||
                data.getParticulateMatter() > 20;

        data.setAlert(alert);
        data.setTimestamp(LocalDateTime.now());

        if (alert) {
            sendAlert(data);
        }

        return repository.save(data);
    }

    public List<EmissionData> getAllData() {
        return repository.findAll();
    }

    public List<EmissionData> getAlerts() {
        return repository.findByIsAlertTrue();
    }

    public List<EmissionData> getDataByMachineId(String machineId) {
        return repository.findByMachineId(machineId);
    }

    private void sendAlert(EmissionData data) {
        String message = String.format(
                "⚠️ ALERT: Machine %s emissions high! CO: %.2f, NOx: %.2f, PM: %.2f",
                data.getMachineId(),
                data.getCarbonMonoxide(),
                data.getNitrogenOxides(),
                data.getParticulateMatter()
        );

        snsClient.publish(PublishRequest.builder()
                .topicArn(TOPIC_ARN)
                .message(message)
                .subject("Emission Alert")
                .build());
    }
}
