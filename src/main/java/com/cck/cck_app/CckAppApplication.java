package com.cck.cck_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CckAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CckAppApplication.class, args);
	}

}
