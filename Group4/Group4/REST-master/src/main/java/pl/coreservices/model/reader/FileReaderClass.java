package pl.coreservices.model.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hazelcast.core.IMap;
import org.springframework.context.ConfigurableApplicationContext;
import pl.coreservices.model.web.Statistic;
import pl.coreservices.service.Receiver;
import pl.coreservices.service.Sender;

public class FileReaderClass {

    private static final String QUEUE_NAME = "MessageQueue";
    private ConfigurableApplicationContext contex;
    private IMap<String, Statistic> database;

    public FileReaderClass(ConfigurableApplicationContext contex, IMap<String, Statistic> database) {
        this.contex = contex;
        this.database = database;
    }

    public void readFile(String name) {

        String fileName = name;
        String line;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

                Pattern MY_PATTERN = Pattern.compile("#(\\w+)");
                Matcher mat = MY_PATTERN.matcher(line);
                List<String> strs = new ArrayList<>();
                while (mat.find()) {
                    strs.add(mat.group(1));
                }

                for (String s : strs) {
                    if (database.containsKey(s)) {
                        database.get(s).AddToCounter();
                    } else {
                        database.put(s, new Statistic(s, 1));
                    }

                }
            }
            Sender sender = contex.getBean(Sender.class);
            Receiver receiver = contex.getBean(Receiver.class);
            for (IMap.Entry<String, Statistic> map : database.entrySet()) {
                sender.sendMessage(QUEUE_NAME, map.getKey());
                receiver.receiveMessage(QUEUE_NAME);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }


}
