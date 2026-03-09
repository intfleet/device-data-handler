package com.intellifleet.cgw.test;


import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.OutputStream;

@UtilityClass
public class GPSData {

//    String data = "IMEI=123456789012345,LAT=22.5726,LON=88.3639,SPEED=45\n";
    String data = "$,dLite,LVFu,NR,L,861230041743650,WB-14-5706,1,220924,130520,22.675432,N,88.453421,E,0000.0,109.7,356,18,2.76,1.98,airtel,4G,18,12.8,3.43,10101,1101,FT1:70[temp high];RF:AB12E043;FL1:800,FT1:70[temp high];RF:AB12E043;FL1:800,FT1:70[temp high];RF:AB12E043;FL1:800,0000,CI,407,475,2,*\n";

    public void sendData(OutputStream os, int count) {
        try {
            int pos = 0;
            while(pos++ <= count) {
                os.write(data.getBytes());
                os.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
