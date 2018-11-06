package pl.coreservices.BootCampHashTag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.coreservices.configuration.WebAppConfiguration;
import pl.coreservices.service.FileWatchService;

@SpringBootApplication
@Import({WebAppConfiguration.class})
public class BootCampHashTagApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootCampHashTagApplication.class, args);


		FileWatchService fileWatch = new FileWatchService();
		fileWatch.watchDirectoryPath();




	}
}
