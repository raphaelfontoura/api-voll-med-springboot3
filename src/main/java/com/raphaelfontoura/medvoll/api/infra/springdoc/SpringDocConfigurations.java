package com.raphaelfontoura.medvoll.api.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components()
                .addSecuritySchemes("bearer-key",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
            ).info(new Info()
                .title("Voll.med API")
                .description("API Rest da aplicação Voll.med")
                .contact(new Contact()
                        .name("Raphael Fontoura")
                        .email("raphael.fontoura@gmail.com"))
                .license(new License()
                        .name("The MIT License")
                        .identifier("MIT")
                        .url("https://www.mit.edu/~amini/LICENSE.md")));
    }
}
