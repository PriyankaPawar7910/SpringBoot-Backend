package com.insurancepolicy;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
/**
 * @author priypawa
 *
 */
@SpringBootApplication
public class InsuranceServiceApplication extends SpringBootServletInitializer{
	/**
	 * This method create {@link ApplicationContext} instance. Start up
	 * {@link SpringBootApplication}
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(InsuranceServiceApplication.class, args);
	}
	/**
	 * This method creates object of {@link RestTemplate}
	 * 
	 * @return : {@link RestTemplate}
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	/**
	 * This method sets up active and default properties for environment of an
	 * application. Builder of {@link SpringApplication} and
	 * {@link ApplicationContext}
	 */
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	       return application.sources(InsuranceServiceApplication.class);
	    }


}
