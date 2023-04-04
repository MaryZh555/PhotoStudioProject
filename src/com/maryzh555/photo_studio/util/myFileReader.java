package com.maryzh555.photo_studio.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author by Zhang M. on 04.04.2023.
 */
public class myFileReader {

    public String extractDescription(Object object, String filePath) {
        try {
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts[0].equalsIgnoreCase(object.toString())) {
                    return parts[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR catch");
        }
        return "";
    }
}
