package com.dfz.service.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName GatewayApplication
 * @Description GatewayApplication
 * @Author dfz
 * @Date 2019-11-01 10:11
 * @Version 1.0
 **/
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        return builder.routes().route(p -> p
//                .path("/hi")
//                .filters(f -> f.addRequestHeader("Hello", "World"))
//                .uri("http://localhost:8763")).build();
//    }

}
