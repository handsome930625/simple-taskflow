package com.charse.taskflow.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

/**
 * @author chaser
 */
public class ResourceUtils {

    private static PathMatchingResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();

    public static Resource[] getResources(String locationPattern) {
        try {
            return resourceLoader.getResources(locationPattern);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}