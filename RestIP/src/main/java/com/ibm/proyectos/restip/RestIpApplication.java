package com.ibm.proyectos.restip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RestIpApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestIpApplication.class, args);
	}

}
