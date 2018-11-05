package com.coreservices.BootcampHashTag;

import com.coreservices.configuration.WebAppConfiguration;
import com.coreservices.configuration.FileProcessorConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({WebAppConfiguration.class, FileProcessorConfiguration.class})
public class BootcampHashTagApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootcampHashTagApplication.class, args);
	}
}
