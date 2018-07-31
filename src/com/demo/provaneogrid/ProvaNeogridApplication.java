package com.demo.provaneogrid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableCaching
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ProvaNeogridApplication {

	public static void main (String args[]) {
		System.out.println("INICIALIZANDO APLICACAO...");
		SpringApplication.run(ProvaNeogridApplication.class, args);		
	}
	
}
