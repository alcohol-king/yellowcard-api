package com.depromeet.yellowcardapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class YellowcardApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(YellowcardApiApplication.class, args);
	}

}

