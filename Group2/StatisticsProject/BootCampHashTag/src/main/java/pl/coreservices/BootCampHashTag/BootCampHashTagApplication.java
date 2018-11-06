package pl.coreservices.BootCampHashTag;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.coreservices.configuration.WebAppConfiguration;
import pl.coreservices.model.web.Consumer;
import pl.coreservices.model.web.Producer;
import pl.coreservices.service.FileWatchService;

import javax.jms.*;


@SpringBootApplication
@Import({WebAppConfiguration.class})
public class BootCampHashTagApplication {

	public static void main(String[] args) throws JMSException {
		SpringApplication.run(BootCampHashTagApplication.class, args);

        //Producer prod = new Producer();

        //prod.sendMessage("elo");

		FileWatchService fileWatch = new FileWatchService();

		Consumer consumer = new Consumer();

		consumer.listen();


	}
}
