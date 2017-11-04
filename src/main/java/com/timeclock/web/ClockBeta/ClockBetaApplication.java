package com.timeclock.web.ClockBeta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Bean
public WebMvcConfigurer corsConfigurer() {
	return new WebMvcConfigurerAdapter() {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:3000");
		}
	};
}

@SpringBootApplication
public class ClockBetaApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClockBetaApplication.class, args);
	}
}
