package com.buyzilla.services.buyzillaapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BuyzillaApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuyzillaApiGatewayApplication.class, args);
    }

}
