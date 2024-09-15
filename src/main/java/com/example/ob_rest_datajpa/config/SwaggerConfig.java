package com.example.ob_rest_datajpa.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                        .info(new Info()
                        .title("SpringBoot API")
                        .description("Spring Boot Swagger Example")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")
                        .url("http://springdoc.org")))
                        .externalDocs(new ExternalDocumentation()
                        .description("SpringBoot Swager Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }
}
