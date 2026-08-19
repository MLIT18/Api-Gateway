package com.insightzz.apigateway.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class GatewayExceptionHandler {

    public ServerResponse handle(
            ServerRequest request,
            Exception exception) {

        Map<String, Object> response =
                new LinkedHashMap<>();

        response.put(
                "timestamp",
                OffsetDateTime.now()
        );

        response.put(
                "status",
                HttpStatus.BAD_GATEWAY.value()
        );

        response.put(
                "error",
                "BAD_GATEWAY"
        );

        response.put(
                "message",
                "Unable to process gateway request"
        );

        response.put(
                "path",
                request.path()
        );

        return ServerResponse
                .status(HttpStatus.BAD_GATEWAY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
