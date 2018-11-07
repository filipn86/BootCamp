package pl.coreservices.model.web;

import com.google.common.base.Charsets;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.io.Files.readLines;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

@Component
public class FileParser {
    private static final String REGEX = "#(\\w+)";


    public ArrayList<String> extractHashTags(String fileRow) {
    	
    	Pattern patt = Pattern.compile(REGEX);
        Matcher match = patt.matcher(fileRow);
        ArrayList<String> matStr =new ArrayList<String>();
        while (match.find()) {
            matStr.add(match.group(1));
       }
        
        return matStr;
         
    }

    public List <String> parseFileRows(String fileToParse) {

        List <String> statisticData = new ArrayList <>();
        try {
            statisticData = readLines(new File(fileToParse), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return statisticData;
    }


}
