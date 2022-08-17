package com.codestates.soloprojectkihong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SoloProjectKihongApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoloProjectKihongApplication.class, args);
	}

}
