package com.iokays.dispatch.core.adapter.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.oauth2.sdk.util.MapUtils;
import io.vavr.control.Try;
import org.quartz.JobDataMap;

public class JobUtils {

    public static final String INPUT_DATA = "input";
    public static final String OUTPUT_DATA = "output";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(final Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T getInputData(final JobDataMap jobDataMap, final Class<T> clazz) {
        if (MapUtils.isEmpty(jobDataMap)) {
            return null;
        }
        return Try.of(() -> objectMapper.readValue(jobDataMap.getString(INPUT_DATA), clazz)).get();
    }



}
