package com.insightzz.apigateway.filter;

import com.insightzz.apigateway.filter.CorrelationIdFilter;
import com.insightzz.apigateway.filter.RequestLoggingFilter;
import com.insightzz.apigateway.filter.SecurityHeadersFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterOrderConfig {

    @Bean
    public FilterRegistrationBean<CorrelationIdFilter>
    correlationIdFilterRegistration(
            CorrelationIdFilter filter) {

        FilterRegistrationBean<CorrelationIdFilter> registration =
                new FilterRegistrationBean<>();

        registration.setFilter(filter);
        registration.setOrder(1);

        return registration;
    }

    @Bean
    public FilterRegistrationBean<RequestLoggingFilter>
    requestLoggingFilterRegistration(
            RequestLoggingFilter filter) {

        FilterRegistrationBean<RequestLoggingFilter> registration =
                new FilterRegistrationBean<>();

        registration.setFilter(filter);
        registration.setOrder(2);

        return registration;
    }

    @Bean
    public FilterRegistrationBean<SecurityHeadersFilter>
    securityHeadersFilterRegistration(
            SecurityHeadersFilter filter) {

        FilterRegistrationBean<SecurityHeadersFilter> registration =
                new FilterRegistrationBean<>();

        registration.setFilter(filter);
        registration.setOrder(3);

        return registration;
    }
}
