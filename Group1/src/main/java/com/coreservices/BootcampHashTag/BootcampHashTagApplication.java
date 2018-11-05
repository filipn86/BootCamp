package com.coreservices.BootcampHashTag;

import com.coreservices.configuration.WebAppConfiguration;
import com.coreservices.service.FileProcessorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({WebAppConfiguration.class, FileProcessorService.class})
public class BootcampHashTagApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootcampHashTagApplication.class, args);
	}
}
