package pl.coreservices.model.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.coreservices.model.web.StatisticsListEnum;

public class FileReaderClass {
	
	public void readFile(String name) {
		
		   // The name of the file to open.
        String fileName = name;

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
                	if (StatisticsListEnum.statlist.contains(s)) {
                		StatisticsListEnum.statlist.addCount(s);
                	}
                	else {
                		StatisticsListEnum.statlist.addHash(s);
                	}
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
