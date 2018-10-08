package com.spaceprogram;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJSONDoc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.spaceprogram")
public class SpaceprogramApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpaceprogramApplication.class, args);
		
		new SpringApplicationBuilder(SpaceprogramApplication.class).properties("spring.config.name:spaceprogram").build().run(args);
	}
}
