package pl.coreservices.BootCampHashTag;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import pl.coreservices.FileListener.FileListener;
import pl.coreservices.configuration.WebAppConfiguration;
import pl.coreservices.model.web.StatisticsListEnum;

//Fileloading is happening outside Spring
@SpringBootApplication
@Import({WebAppConfiguration.class})
public class BootCampHashTagApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(BootCampHashTagApplication.class, args);
		
		StatisticsListEnum stats = new StatisticsListEnum(); //necessary, initialize StatisticsListEnum
		context.getBean(FileListener.class).readFirstFiles();
		context.getBean(FileListener.class).run();
	}
}
