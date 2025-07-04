package com.caterpillar.emissions.service;

import com.caterpillar.emissions.model.EmissionData;
import com.caterpillar.emissions.repository.EmissionRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmissionService {

    @Autowired
	private EmissionRepository repository;

    // Example thresholds
    private static final double CO_LIMIT = 50.0;
    private static final double NOX_LIMIT = 40.0;
    private static final double PM_LIMIT = 20.0;

    public EmissionData processEmissionData(EmissionData data) {
        boolean alert = data.getCarbonMonoxide() > CO_LIMIT ||
                        data.getNitrogenOxides() > NOX_LIMIT ||
                        data.getParticulateMatter() > PM_LIMIT;

        data.setAlert(alert);
        data.setTimestamp(LocalDateTime.now());

        if (alert) {
            System.out.println("ALERT: High emissions detected from machine " + data.getMachineId());
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
}
