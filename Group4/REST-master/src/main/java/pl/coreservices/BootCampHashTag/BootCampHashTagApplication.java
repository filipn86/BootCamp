package pl.coreservices.BootCampHashTag;

import java.io.IOException;
import java.util.List;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import pl.coreservices.FileListener.FileListener;
import pl.coreservices.configuration.WebAppConfiguration;
import pl.coreservices.model.web.Statistic;
import pl.coreservices.model.web.StatisticsListEnum;
import pl.coreservices.service.Receiver;
import pl.coreservices.service.Sender;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@Import({WebAppConfiguration.class})
public class BootCampHashTagApplication {


	@Bean
	public ConnectionFactory connectionFactory() {
		ConnectionFactory connectionFactory =
				new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
		return connectionFactory;
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		ConfigurableApplicationContext context = SpringApplication.run(BootCampHashTagApplication.class, args);
		FileListener fl = new FileListener(context);
		fl.readFirstFiles();
		fl.run();
	}
}
