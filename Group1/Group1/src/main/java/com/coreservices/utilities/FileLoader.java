package com.coreservices.utilities;

import com.coreservices.activemq.Producer;
import com.coreservices.database.Database;
import com.coreservices.model.NodeStatistics;
import com.coreservices.model.Statistic;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class FileLoader {
    private Database database;
    private IMap <String, Integer> hashTagMap;
    private NodeStatistics nodeStatistics;

    @Autowired
    public FileLoader(Database database) {
        this.database = database;
    }

    public void getHashTagsFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader
                     = new BufferedReader(new InputStreamReader(new FileInputStream("listen\\" + fileName), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileContent = builder.toString();
        Pattern hashTag = Pattern.compile("#([A-Za-z0-9_-]+)");
        Matcher matcher = hashTag.matcher(fileContent);
        int index = 1;
        while (matcher.find()) {
            try {
                Producer.sendToQueue(matcher.group());
                database.add(new Statistic(matcher.group()));
                nodeStatistics = NodeStatistics.getInstance();
                nodeStatistics.addToStatisticMap(index, new Statistic(matcher.group()));
                index++;
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
