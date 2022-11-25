package com.sweep.surveyservice.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@OpenAPIDefinition(
        servers = { @Server(url = "http://localhost:8762/survey"),
                @Server(url = "http://210.109.60.247:10023/survey"),
                @Server(url = "http://210.109.61.31:10010/survey"),
                @Server(url = "http://210.109.63.227:10001/survey")},
        info = @Info(
                title = "설문 서비스",
                description = "설문 서비스 \"폼듀\" API 명세서",
                version = "v1"),
        security = @SecurityRequirement(name = "Bearer Authentication"))

@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {
}


