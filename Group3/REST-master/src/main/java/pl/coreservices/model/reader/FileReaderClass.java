package pl.coreservices.model.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coreservices.messaging.MessageSender;
import pl.coreservices.model.web.StatisticsListEnum;

@Component
public class FileReaderClass {

    private MessageSender messageSender;

    @Autowired
    public FileReaderClass(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void readFile(String name) {
		
		   // The name of the file to open.
        String fileName = name;
        System.out.println("File readed: " + fileName);

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                
                Pattern MY_PATTERN = Pattern.compile("#(\\w+)");
                Matcher mat = MY_PATTERN.matcher(line);
                List<String> strs=new ArrayList<String>();
                while (mat.find()) {
                  //System.out.print(mat.group(1));
                  strs.add(mat.group(1));
                }
                
                for(String s: strs){
                    messageSender.sendMessage(s);
                }
       
            }
            StatisticsListEnum.statlist.print();
            

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
		

}
