package com.gameloft.profilematcher.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;

public class JSONUtil {

    public static <T> T getPOJOFromJsonFile(String fileName, Class<T> pojoObject) {
        try {
            File file = ResourceUtils.getFile(String.format("classpath:%s", fileName));
            ObjectMapper om = new ObjectMapper();
            return om.readValue(file, pojoObject);
        } catch (Exception e) {
            System.out.println("Could not parse file to object");
        }

        return null;
    }
}
