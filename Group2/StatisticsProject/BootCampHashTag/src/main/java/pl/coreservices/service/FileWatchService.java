package pl.coreservices.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import pl.coreservices.model.web.FileValidator;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.*;

@RestController
public class FileWatchService {

    @Value("${folder}")
    private String filePath;


    private FileValidator fileValidator = new FileValidator();

    public void watchDirectoryPath() {

        Path path = Paths.get(filePath);
        if (!fileValidator.isCorrectDirectory(path)) {
            return;
        }
        System.out.println("Watching path: " + path);
        FileSystem fs = path.getFileSystem();
        try (WatchService service = fs.newWatchService()) {
            path.register(service, ENTRY_CREATE);
            path.register(service, ENTRY_DELETE);
            path.register(service, ENTRY_MODIFY);
            WatchKey key;
            while (true) {
                key = service.take();
                for (WatchEvent <?> watchEvent : key.pollEvents()) {
                    String watchedFile = watchEvent.context().toString();
                    List <String> hashTagList =
                            fileValidator.parseFileRows(filePath + "\\" + watchedFile);
                    hashTagList.forEach(fileValidator::extractHashTags);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
