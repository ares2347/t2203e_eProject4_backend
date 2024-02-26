package com.eproject.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI configuration(){
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        SecurityRequirement security = new SecurityRequirement();
        security.addList("Bearer Authentication");

        return new OpenAPI()
                .addSecurityItem(security)
                .components(new Components().addSecuritySchemes(
                        "Bearer Authentication", createAPIKeyScheme()
                ))
                .info(new Info().title("SEM4 EProject API")
                        .description("SEM4 EProject API")
                        .version("1.0"))
                .servers(List.of(server));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
