package com.example.AWSReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AwsReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsReaderApplication.class, args);
	}

}
