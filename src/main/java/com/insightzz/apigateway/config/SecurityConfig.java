package com.insightzz.apigateway.config;

import com.insightzz.apigateway.security.CustomJwtAuthenticationConverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http

                // =================================================
                // CORS
                // =================================================

                .cors(Customizer.withDefaults())


                // =================================================
                // CSRF
                // =================================================

                .csrf(csrf -> csrf.disable())


                // =================================================
                // STATELESS
                // =================================================

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )


                // =================================================
                // AUTHORIZATION
                // =================================================

                .authorizeHttpRequests(auth -> auth

                        // CORS preflight
                        .requestMatchers(HttpMethod.OPTIONS, "/**")
                        .permitAll()

                        // Gateway actuator
                        .requestMatchers(
                                "/actuator/health",
                                "/actuator/info"
                        ).permitAll()

                        // Login
                        .requestMatchers(
                                "/api/v1/auth/login"
                        ).permitAll()

                        // User service health
                        .requestMatchers(
                                "/api/v1/users/health"
                        ).permitAll()

                        // Everything else requires JWT
                        .anyRequest().authenticated()
                )


                // =================================================
                // CUSTOM JWT
                // =================================================

                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(
                                        jwtAuthenticationConverter()
                                )
                        )
                );

        return http.build();
    }


    // =============================================================
    // JWT AUTHENTICATION CONVERTER
    // =============================================================

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        JwtAuthenticationConverter converter =
                new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(
                new CustomJwtAuthenticationConverter()
        );

        return converter;
    }
}