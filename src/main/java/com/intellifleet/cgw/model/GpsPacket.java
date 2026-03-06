package com.intellifleet.cgw.model;

import lombok.Data;

@Data
public class GpsPacket {

    private String imei;
    private double lat;
    private double lon;
    private int speed;
    private long timestamp;

    // getters & setters

    @Override
    public String toString() {
        return "GpsPacket{" +
                "imei='" + imei + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", speed=" + speed +
                ", timestamp=" + timestamp +
                '}';
    }
}

