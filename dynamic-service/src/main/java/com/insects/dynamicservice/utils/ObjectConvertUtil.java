package com.insects.dynamicservice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ObjectConvertUtil {
    private static final Logger log = LoggerFactory.getLogger(ObjectConvertUtil.class);
    private static ThreadLocal<ObjectMapper> threadLocal = new ThreadLocal() {
        protected synchronized ObjectMapper initialValue() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper;
        }
    };

    public ObjectConvertUtil() {
    }

    public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        return ((ObjectMapper) threadLocal.get()).convertValue(fromValue, toValueType);
    }

    public static <T> T readValue(String fromValue, Class<T> toValueType) throws IOException {
        return ((ObjectMapper) threadLocal.get()).readValue(fromValue, toValueType);
    }

    public static <T> T readValue(String fromValue, TypeReference typeReference) throws IOException {
        return (T) ((ObjectMapper) threadLocal.get()).readValue(fromValue, typeReference);
    }

    public static <T> T convertValue(Object fromValue, TypeReference<?> toValueTypeRef) {
        return (T) ((ObjectMapper) threadLocal.get()).convertValue(fromValue, toValueTypeRef);
    }

    public static String writeValueAsString(Object value) {
        try {
            return ((ObjectMapper) threadLocal.get()).writeValueAsString(value);
        } catch (JsonProcessingException var2) {
            if (log.isInfoEnabled()) {
                log.info("json exception " + var2.getMessage());
            }

            return null;
        }
    }
}
