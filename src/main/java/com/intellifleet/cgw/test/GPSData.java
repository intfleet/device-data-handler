package com.intellifleet.cgw.test;


import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.OutputStream;

@UtilityClass
public class GPSData {

    String data = "IMEI=123456789012345,LAT=22.5726,LON=88.3639,SPEED=45\n";

    public void sendData(int taskNo, OutputStream os, int count) {
        try {
            String taskName = "TASK-"+taskNo;
            int pos = 0;
            while(pos++ <= count) {
                os.write((taskName + ":::LINE-"+ pos+ "," + data).getBytes());
                os.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopSending(OutputStream os) {
        try {
            os.write("STOP".getBytes());
            os.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
