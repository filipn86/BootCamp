package pl.coreservices.model.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("kafka")
public class KafkaConsumer implements Consumer{

    @Override
    public void listen() {

    }
}
