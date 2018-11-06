package com.coreservices.configuration;

import com.coreservices.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.messaging.MessageChannel;
import com.coreservices.utilities.LastModifiedFileFilter;

import java.io.File;

@Configuration
public class FileProcessorConfiguration {
    private static final String DIRECTORY = "listen";

    @Bean
    public IntegrationFlow processFileFlow() {
        return IntegrationFlows
                .from(s -> s.file(new File("listen"))
                                .filter(new LastModifiedFileFilter()),
                        e -> e.poller(Pollers.fixedDelay(1000)))
                .transform(fileToStringTransformer())
                .handle("fileProcessor", "process").get();
    }

    @Bean
    public MessageChannel fileInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public FileToStringTransformer fileToStringTransformer() {
        return new FileToStringTransformer();
    }
}
