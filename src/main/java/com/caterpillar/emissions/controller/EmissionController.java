package com.caterpillar.emissions.controller;

import com.caterpillar.emissions.model.EmissionData;
import com.caterpillar.emissions.service.EmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emissions")
@RequiredArgsConstructor
public class EmissionController {
    private final EmissionService service;

    @PostMapping
    public ResponseEntity<EmissionData> submitEmission(@RequestBody EmissionData data) {
        return ResponseEntity.ok(service.processEmissionData(data));
    }

    @GetMapping
    public ResponseEntity<List<EmissionData>> getAllEmissions() {
        return ResponseEntity.ok(service.getAllData());
    }

    @GetMapping("/alerts")
    public ResponseEntity<List<EmissionData>> getEmissionAlerts() {
        return ResponseEntity.ok(service.getAlerts());
    }

    @GetMapping("/machine/{machineId}")
    public ResponseEntity<List<EmissionData>> getEmissionsByMachine(@PathVariable String machineId) {
        return ResponseEntity.ok(service.getDataByMachineId(machineId));
    }
}
