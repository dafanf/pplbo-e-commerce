package com.ecommerce.apigateway.routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Routes {

    private static final Logger logger = LoggerFactory.getLogger(Routes.class);

    @Bean
    public RouteLocator serviceRoutes(RouteLocatorBuilder builder) {
        logger.info("Configuring routes for all services");

        return builder.routes()
                .route("payment_service", r -> r.path("/api/payments/**")
                        .filters(f -> {
                            logger.info("Applying filters for payment service route");
                            return f.rewritePath("/api/(?<segment>.*)", "/${segment}");
                        })
                        .uri("http://localhost:8080"))
                .route("order_service", r -> r.path("/api/orders/**")
                        .filters(f -> {
                            logger.info("Applying filters for order service route");
                            return f.rewritePath("/api/(?<segment>.*)", "/${segment}");
                        })
                        .uri("http://localhost:8081"))
                .route("order_service", r -> r.path("/api/customer/**")
                        .filters(f -> {
                            logger.info("Applying filters for customer service route");
                            return f.rewritePath("/api/(?<segment>.*)", "/${segment}");
                        })
                        .uri("http://localhost:8081"))
                .route("order_service", r -> r.path("/api/shipping/**")
                        .filters(f -> {
                            logger.info("Applying filters for shipping service route");
                            return f.rewritePath("/api/(?<segment>.*)", "/${segment}");
                        })
                        .uri("http://localhost:8081"))
                .route("promotion_service", r -> r.path("/api/promotions/**")
                        .filters(f -> {
                            logger.info("Applying filters for promotion service route");
                            return f.rewritePath("/api/(?<segment>.*)", "/${segment}");
                        })
                        .uri("http://localhost:8082"))
                .route("product_service", r -> r.path("/api/product/**")
                        .filters(f -> {
                            logger.info("Applying filters for product service route");
                            return f.rewritePath("/api/(?<segment>.*)", "/${segment}");
                        })
                        .uri("http://localhost:8083"))
                .route("cart_service", r -> r.path("/api/cart/**")
                        .filters(f -> {
                            logger.info("Applying filters for cart service route");
                            return f.rewritePath("/api/(?<segment>.*)", "/${segment}");
                        })
                        .uri("http://localhost:8084"))
                .build();
    }
}
