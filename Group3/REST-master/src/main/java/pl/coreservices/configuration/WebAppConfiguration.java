package pl.coreservices.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.coreservices.service.StatisticsService;

@Configuration
@ComponentScan(basePackages = {"pl.coreservices.web"})
public class WebAppConfiguration {

    @Bean
    public StatisticsService statisticsService() {
        return new StatisticsService();
    }
}
