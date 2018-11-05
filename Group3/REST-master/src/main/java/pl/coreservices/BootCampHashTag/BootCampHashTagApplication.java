package pl.coreservices.BootCampHashTag;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import pl.coreservices.FileListener.FileListener;
import pl.coreservices.configuration.WebAppConfiguration;
import pl.coreservices.model.web.StatisticsListEnum;

@SpringBootApplication
@Import({WebAppConfiguration.class})
public class BootCampHashTagApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(BootCampHashTagApplication.class, args);
		
		StatisticsListEnum stats = new StatisticsListEnum(); //necessary, initialize StatisticsListEnum
		
		FileListener fl = new FileListener();
		fl.readFirstFiles();
		fl.run(); 

	}
}
