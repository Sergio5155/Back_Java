package com.olympoburgers.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("📡 URL: " + env.getProperty("spring.datasource.url"));
		System.out.println("👤 USER: " + env.getProperty("spring.datasource.username"));
		System.out.println("🔒 PASSWORD: " + env.getProperty("spring.datasource.password"));
	}
}
