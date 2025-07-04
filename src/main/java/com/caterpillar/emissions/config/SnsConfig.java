package com.caterpillar.emissions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class SnsConfig {

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder()
                .region(Region.US_WEST_2)  // Replace with your AWS region (e.g., US_EAST_1, AP_SOUTH_1)
                .build();
    }
}
