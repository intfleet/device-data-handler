package com.intellifleet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.intellifleet")
public class DeviceDataHandlerMain {
    public static void main(String[] args) {
        SpringApplication.run(DeviceDataHandlerMain.class, args);
    }
}