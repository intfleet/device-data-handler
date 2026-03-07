package com.intellifleet.cgw.test;

import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Executors;

public class GpsDeviceSimulator {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 9000);
        OutputStream os = socket.getOutputStream();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 1; i <= 10; i++) {
                executor.submit(() -> GPSData.sendData(os, 10000));
            }

        }



        socket.close();
        System.out.println("GPS data sent");
    }
}

