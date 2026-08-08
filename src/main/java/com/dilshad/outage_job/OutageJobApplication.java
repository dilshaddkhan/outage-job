package com.dilshad.outage_job;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OutageJobApplication implements CommandLineRunner {

	public static void main(String[] args) {
		ConfigurableApplicationContext context= SpringApplication.run(OutageJobApplication.class, args);
		int exitCode=SpringApplication.exit(context, ()->0);
		System.exit(exitCode);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
