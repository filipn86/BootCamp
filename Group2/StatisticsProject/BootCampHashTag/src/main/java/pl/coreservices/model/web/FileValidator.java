package pl.coreservices.model.web;

import com.google.common.base.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.io.Files.readLines;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

@Component
public class FileValidator {
    private static final String HASH_REGEX = "#";
    private static final String BLANK_SPACE = " ";


    private StatisticsList statisticsList = StatisticsList.getInstance();

    public void extractHashTags(String fileRow, Producer prod)  {

        String hashTag = "";
        while (hashTag != null) {
            hashTag = StringUtils.substringBetween(fileRow, HASH_REGEX, BLANK_SPACE);
            if (hashTag != null) {
                statisticsList.addStatistic(new Statistic.Builder()
                        .name(hashTag)
                        .count(1)
                        .build());
                    prod.sendMessage(hashTag);
                }
            }
            hashTag = hashTag.replace(HASH_REGEX + hashTag, "");
        }


    public boolean isCorrectDirectory(Path path) {

        try {
            Boolean isFolder = (Boolean) Files.getAttribute(path,
                    "basic:isDirectory", NOFOLLOW_LINKS);
            if (!isFolder) {
                throw new IllegalArgumentException("Path: " + path
                        + " is not a folder");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return true;
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
