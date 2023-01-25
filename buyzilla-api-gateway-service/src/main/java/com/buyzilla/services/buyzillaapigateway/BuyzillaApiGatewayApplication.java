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

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {

		return builder.routes()
				.route(p -> p
						.path("/api/v1/products")
						.uri("http://localhost:2032/api/v1/products")
				).route(p -> p
				.path("/api/v1/suppliers")
				.uri("http://localhost:2031/api/v1/suppliers")
		)
				.build();
	}

}
