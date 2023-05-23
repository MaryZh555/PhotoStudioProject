package main.com.maryzh555.photo_studio.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;

/**
 * @author by Zhang M. on 04.04.2023.
 */
public class myFileReader {

    public String extractDescription(Object object, String filePath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            if (inputStream != null) {
                InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(streamReader);
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts[0].equalsIgnoreCase(object.toString())) {
                        return parts[1];
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR catch --- myFileReader");
        }
        return "-- ERROR: No file found---";
    }
}
