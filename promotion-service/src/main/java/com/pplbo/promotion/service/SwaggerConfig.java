package com.pplbo.promotion.service;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI defineOpenApi() {
 
        Info information = new Info()
                .title("Promotions Service API")
                .version("1.0")
                .description("List API for Promotions Service.");

        return new OpenAPI().info(information);
    }
}