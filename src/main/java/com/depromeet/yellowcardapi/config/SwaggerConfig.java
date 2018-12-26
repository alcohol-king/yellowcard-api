package com.depromeet.yellowcardapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static String DEFAULT_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTQ1ODE5MzE4fQ.XhQV5F91vYB3W5a4cuIi9Io1w7Yt_FuxYUd_uwmE7V4";

    @Bean
    public Docket api() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name("token")
                        .parameterType("header")
                        .defaultValue(DEFAULT_TOKEN)
                        .modelRef(new ModelRef("string"))
                        .required(true)
                        .build();

        ArrayList<Parameter> parameters = new ArrayList<>();

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(parameters)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.depromeet.yellowcardapi"))
                .paths(PathSelectors.any())
                .build();
    }
}