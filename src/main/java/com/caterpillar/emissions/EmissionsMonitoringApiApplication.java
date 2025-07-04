package com.caterpillar.emissions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmissionsMonitoringApiApplication {

	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(10000);
		SpringApplication.run(EmissionsMonitoringApiApplication.class, args);
	}
}
