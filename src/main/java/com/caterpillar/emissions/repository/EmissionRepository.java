package com.caterpillar.emissions.repository;

import com.caterpillar.emissions.model.EmissionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmissionRepository extends JpaRepository<EmissionData, Long> {
    List<EmissionData> findByMachineId(String machineId);
    List<EmissionData> findByIsAlertTrue();
}
