package pl.coreservices.model.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Configuration
@Profile("kafka")
public class KafkaProducer implements Producer {
    @Override
    public void sendMessage(String text) {
    }

    public KafkaProducer() {

    }
}
