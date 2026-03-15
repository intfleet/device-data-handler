package com.intellifleet.cgw.test;

import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GpsDeviceSimulator {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("192.168.100.251", 9000);
//        Socket socket = new Socket("localhost", 9000);
        OutputStream os = socket.getOutputStream();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<?>> futures = new ArrayList<>();

            long start = System.currentTimeMillis();
            GPSData.sendOnlyData(os, "START");
            for (int i = 1; i <= 10; i++) {
                Future<?> submit = executor.submit(() -> GPSData.sendData(os, 10000));
            }

            // wait for all threads complete
            for (Future<?> future : futures) {
                future.get();
            }
            GPSData.sendOnlyData(os, "END");
            long end = System.currentTimeMillis();

            System.out.println("All threads completed");
            System.out.println("Start Time : " + start);
            System.out.println("End Time   : " + end);
            System.out.println("Total Time : " + (end - start) + " ms");

        }


//        GPSData.sendData(os, 2);


        socket.close();
        System.out.println("GPS data sent");
    }
}

