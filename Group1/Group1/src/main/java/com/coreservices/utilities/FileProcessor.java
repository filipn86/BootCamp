package com.coreservices.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class FileProcessor {
    private static final String HEADER_FILE_NAME = "file_name";
    private FileLoader fl;

    @Autowired
    public FileProcessor(FileLoader fl) {
        this.fl = fl;
    }

    public void process(Message<String> msg) {
        String fileName = (String) msg.getHeaders().get(HEADER_FILE_NAME);

        fl.getHashTagsFromFile(fileName);
    }
}
