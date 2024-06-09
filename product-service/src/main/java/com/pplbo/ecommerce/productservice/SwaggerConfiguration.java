package com.pplbo.ecommerce.productservice;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {

        Info information = new Info()
                .title("Product Service API")
                .version("1.0")
                .description("List API for Product Service.");

        return new OpenAPI().info(information);
    }
}