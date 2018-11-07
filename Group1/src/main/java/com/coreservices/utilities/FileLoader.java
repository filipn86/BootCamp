package com.coreservices.utilities;

import com.coreservices.activemq.Consumer;
import com.coreservices.activemq.Producer;
import com.coreservices.database.Database;
import com.coreservices.model.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jms.JMSException;

@Component
public class FileLoader {
    private Database database;

    @Autowired
    public FileLoader(Database database) {
        this.database = database;
    }

    public void getHashTagsFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader
                     = new BufferedReader(new InputStreamReader(new FileInputStream("listen\\" + fileName), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null ) {
                builder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileContent = builder.toString();

        Pattern hashTag = Pattern.compile("#([A-Za-z0-9_-]+)");
        Matcher matcher = hashTag.matcher(fileContent);
        while (matcher.find()) {
    
//        	Producer works
        	try {
				Producer.sendToQueue(matcher.group());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
            database.add(new Statistic(matcher.group()));
        }
    }
}
