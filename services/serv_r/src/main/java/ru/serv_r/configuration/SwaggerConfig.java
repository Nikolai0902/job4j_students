package ru.serv_r.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Настройка конфигурации для создания спецификаций на основе Swagger.
 */
@Configuration
public class SwaggerConfig {

    @Value("${server.port}")
    public String port;

    @Value("${application.name}")
    public String applicationName;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .servers(List.of(new Server().url("http://localhost:" + port)))
                .info(new Info().title(applicationName));
    }
 }
