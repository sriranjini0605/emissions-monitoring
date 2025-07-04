package com.caterpillar.emissions.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmissionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String machineId;
    private double carbonMonoxide;
    private double nitrogenOxides;
    private double particulateMatter;
    private LocalDateTime timestamp;

    private boolean isAlert;
}
