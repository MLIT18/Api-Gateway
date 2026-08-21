package com.insightzz.apigateway.config;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;

@Configuration
public class GatewayRouteConfig {

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {

        return GatewayRouterFunctions.route("user-service")

                .route(
                        path("/api/v1/users/**"),
                        http()
                )
                .route(
                        path("/api/v1/roles/**"),
                        http()
                )

                .before(
                        uri("http://localhost:9082")
                )

                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> clientServiceRoute() {

        return GatewayRouterFunctions.route("client-service")

                .route(
                        path("/api/v1/clients/**"),
                        http()
                )

                .before(
                        uri("http://localhost:9083")
                )

                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> projectServiceRoute() {

        return GatewayRouterFunctions.route("project-service")

                .route(
                        path("/api/v1/projects/**"),
                        http()
                )

                .before(
                        uri("http://localhost:9084")
                )

                .build();
    }
}