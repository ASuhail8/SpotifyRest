package com.spotify.oauth.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    public static Properties propertyLoader(String filePath) {

        Properties properties = new Properties();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            properties.load(reader);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}
