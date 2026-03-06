package com.intellifleet.utility;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Component
public class GPSFileUtility {

    Map<String, List<String>> fileMap = new HashMap<>();

    public void recevLine(String line) {
        String fileName = line.split(":::")[0];
        String data = line.split(":::")[1];

        if(!fileMap.containsKey(fileName)){
            fileMap.put(fileName, new ArrayList<>());
        }
        fileMap.get(fileName).add(data);
    }

    public void saveFile() {
        fileMap.keySet().forEach(f -> {
            Path path = Path.of("GpsData-"+f+".txt");
            try {
                Files.writeString(path, String.join("", fileMap.get(f)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
