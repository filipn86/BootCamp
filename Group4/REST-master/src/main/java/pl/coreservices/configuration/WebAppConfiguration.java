package pl.coreservices.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.coreservices.service.Receiver;
import pl.coreservices.service.Sender;
import pl.coreservices.service.StatisticsService;

import javax.jms.ConnectionFactory;

@Configuration
@ComponentScan(basePackages = {"pl.coreservices"})
public class WebAppConfiguration {



    @Bean
    public StatisticsService statisticsService() {
        return new StatisticsService();
    }
}
