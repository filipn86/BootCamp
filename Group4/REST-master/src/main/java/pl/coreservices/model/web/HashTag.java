package pl.coreservices.model.web;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HashTag {
	
	private Map<String, Integer> hashes;
	
	
	public HashTag() {
		hashes = new TreeMap<String, Integer>(); 
	}

    private  Map<String, Integer> searchHashTags(String filePath) throws IOException {

        FileReader fileReader = new FileReader (filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> hashTagList = new ArrayList();
        try {
            String textLine = bufferedReader.readLine();
            Pattern pattern = Pattern.compile(".*?\\s+#(.*?)\\s.*?");
            do {
                Matcher matcher = pattern.matcher(" " + textLine + " ");
                while (matcher.find()) {
                    hashTagList.add(matcher.group(1));
                }
                textLine =  bufferedReader.readLine();
            } while(textLine!=null);
        } finally {
            bufferedReader.close();
        }

        Map<String, Integer> hashTagsMap = new TreeMap<String, Integer>();
        for (String key : hashTagList) {
            Integer counter = hashTagsMap.get(key);
            if(counter == null) {
                counter = 0;
            }
            hashTagsMap.put(key, ++counter);
        }
        return hashTagsMap;
    }
	
	
	
}


