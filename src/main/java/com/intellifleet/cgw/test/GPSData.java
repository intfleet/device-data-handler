package com.intellifleet.cgw.test;


import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class GPSData {

//    String data = "IMEI=123456789012345,LAT=22.5726,LON=88.3639,SPEED=45\n";
    //"$,dLite,LVFu,NR,L,861230041743650,WB-14-5706,1,220924,130520,22.675432,N,88.453421,E,0000.0,109.7,356,18,2.76,1.98,airtel,4G,18,12.8,3.43,10101,1101,FT1:70[temp high];RF:AB12E043;FL1:800,FT1:70[temp high];RF:AB12E043;FL1:800,FT1:70[temp high];RF:AB12E043;FL1:800,0000,CI,407,475,2,*";
    //1. %s = insturment Id pos, 2. %s = date pos, 3. %s = time pos
    String data = "$,dLite,LVFu,NR,L,%s,WB-14-5706,1,%s,%s,22.675432,N,88.453421,E,0000.0,109.7,356,18,2.76,1.98,airtel,4G,18,12.8,3.43,10101,1101,FT1:70[temp high];RF:AB12E043;FL1:800,FT1:70[temp high];RF:AB12E043;FL1:800,FT1:70[temp high];RF:AB12E043;FL1:800,0000,CI,407,475,2,*\n";

    public void sendData(OutputStream os, int count) {
        try {
            int pos = 0;
            while(pos++ <= count) {
                os.write(getData(pos).getBytes());
                os.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    String getData(int pos) {
        String date = StringUtils.right(LocalDate.now().toString().replace("-", ""), 6);
        String time = getTime(pos > 59 ? 0 : pos);
        return String.format(data, getInstrumentId(pos), date, time);
    }

    String getInstrumentId(int pos) {
        //Sample instrument id: "861230041743650"
        return String.format("8612300417%s", String.format("%05d", pos));

    }

    String getTime(int pos) {
        String time = StringUtils.left(LocalDateTime.now().toString().split("T")[1].replace(":", ""), 6);
        time =  StringUtils.left(time, 4)+"%s";
        return String.format(time, String.format("%02d", pos));
    }


    public void sendOnlyData(OutputStream os, String str) {
        try {
            os.write(str.getBytes());
            os.flush();
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

    public static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");

        return LocalDateTime.parse(dateTimeStr, formatter);
    }
}
