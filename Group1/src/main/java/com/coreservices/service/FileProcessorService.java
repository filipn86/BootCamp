package com.coreservices.service;

import com.coreservices.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.messaging.MessageChannel;
import com.coreservices.utilities.LastModifiedFileFilter;
import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
public class FileProcessorService {
    private static final String DIRECTORY = "listen";

    @Autowired
    private Database database;

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
