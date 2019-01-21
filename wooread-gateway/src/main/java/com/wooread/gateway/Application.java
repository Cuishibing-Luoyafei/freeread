package com.wooread.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.RewritePathGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private String url = "";

    @Bean
    public RouteLocator wooreadNovelRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("token_route", r -> r.path("/wooread-novel/**")
                        .filters(f ->
                                f.filter(new RewritePathGatewayFilterFactory()
                                        .apply(new RewritePathGatewayFilterFactory.Config()
                                                .setRegexp("/wooread-novel/(?<segment>.*)")
                                                .setReplacement("/$\\{segment}")))
                                 .filter(new JwtTokenFilter()))
                        .uri("lb://wooread-novel")).build();
    }
}
