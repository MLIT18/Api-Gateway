package com.insightzz.apigateway.util;

import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public final class CorrelationIdUtil {

    public static final String CORRELATION_ID_HEADER = "X-Correlation-ID";

    private CorrelationIdUtil() {
        // Utility class
    }

    /**
     * Returns existing correlation ID from request header.
     * If not present, generates a new UUID.
     */
    public static String getOrCreateCorrelationId(
            HttpServletRequest request) {

        String correlationId =
                request.getHeader(CORRELATION_ID_HEADER);

        if (correlationId == null || correlationId.isBlank()) {
            correlationId = generateCorrelationId();
        }

        return correlationId;
    }

    /**
     * Generates a new correlation ID.
     */
    public static String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
