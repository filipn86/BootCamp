package pl.coreservices.BootCampHashTag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.coreservices.configuration.WebAppConfiguration;

@SpringBootApplication
@Import({WebAppConfiguration.class})
public class BootCampHashTagApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootCampHashTagApplication.class, args);
	}
}
