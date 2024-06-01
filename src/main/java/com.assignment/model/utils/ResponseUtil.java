package com.assignment.utils;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ResponseUtil {

    private static final Logger logger = LoggerFactory.getLogger(ResponseArrayUtils.class);

    private ResponseUtil() {
    }

    public static <T> T[] getResponseArray(Response response, Class<T[]> responseType) {
        if (response == null) {
            logger.error("Response is null");
            throw new IllegalArgumentException("Response cannot be null");
        }
        if (responseType == null) {
            logger.error("Response type is null");
            throw new IllegalArgumentException("Response type cannot be null");
        }
        try {
            T[] result = response.getBody().as(responseType);
            logger.info("Successfully converted response to array of type ", responseType.getSimpleName());
            return result;
        } catch (Exception e) {
            logger.error("Error converting response to array of type ", responseType.getSimpleName(), e.getMessage());
            throw new RuntimeException("Failed to convert response to array", e);
        }
    }
}
