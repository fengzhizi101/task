package com.task.miracletask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MiracletaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiracletaskApplication.class, args);
	}
	
}
