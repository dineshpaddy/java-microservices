package com.au.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SampleApplication extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers (ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/swagger-ui.html");
	}

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}
}
