package com.gameloft.profilematcher.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;

public class JSONUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(JSONUtil.class);

    public static <T> T getPOJOFromJsonString(String json, Class<T> pojoObject) {
        try {
            ObjectMapper om = new ObjectMapper();
            return om.readValue(json, pojoObject);
        } catch (Exception e) {
            LOGGER.error("Could not parse file to object");
        }

        return null;
    }

    public static <T> T getPOJOFromJsonFile(String fileName, Class<T> pojoObject) {
        try {
            File file = ResourceUtils.getFile(String.format("classpath:%s", fileName));
            ObjectMapper om = new ObjectMapper();
            return om.readValue(file, pojoObject);
        } catch (Exception e) {
            LOGGER.error("Could not parse file to object");
        }

        return null;
    }
}
