package com.depromeet.yellowcardapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public class PropertiesEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final String XDG_CONFIG_HOME = System.getenv("XDG_CONFIG_HOME");

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PropertySourceLoader loader = new PropertiesPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try {
            Resource resource = new FileSystemResource(XDG_CONFIG_HOME + "/yellowcard.properties");
            List<PropertySource<?>> propertySources = loader.load("jwt.secret", resource);
            for (PropertySource<?> propertySource : propertySources) {
                environment.getPropertySources().addLast(propertySource);
            }
        } catch (IOException e) {
            logger.warn("외부 설정 파일을 불러오지 못했습니다.");
        }
    }
}
