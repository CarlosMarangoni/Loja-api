package com.gft.loja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LojaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaApiApplication.class, args);
	}

}
