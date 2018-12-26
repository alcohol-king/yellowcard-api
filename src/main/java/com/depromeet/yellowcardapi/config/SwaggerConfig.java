package com.depromeet.yellowcardapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String DEFAULT_TOKEN =
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTQ1ODQxNjM1fQ.VGtvmsfPBvei8GNZmH-UM3OaZrJtTmpg31t0gd2nAOM";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.depromeet.yellowcardapi"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(operationParameters());
    }

    private List<Parameter> operationParameters() {
        List<Parameter> headers = new ArrayList<>();
        headers.add(new ParameterBuilder().name("Authorization")
                .description("for Authorization")
                .modelRef(new ModelRef("string")).parameterType("header")
                .defaultValue(DEFAULT_TOKEN)
                .required(false).build());

        return headers;
    }
}