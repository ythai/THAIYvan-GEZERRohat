package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ThaiyvanGezerrohatAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThaiyvanGezerrohatAppApplication.class, args);
	}

}
