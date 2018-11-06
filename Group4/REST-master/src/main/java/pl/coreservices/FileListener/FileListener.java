package pl.coreservices.FileListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.springframework.context.ConfigurableApplicationContext;
import pl.coreservices.model.reader.FileReaderClass;

public class FileListener {

    public Path watchedDirectory = Paths.get("C:\\BootCamp\\Group3\\Tst");

    private ConfigurableApplicationContext contex;

    public FileListener(ConfigurableApplicationContext contex) {
        this.contex = contex;
    }

    public void run() throws IOException, InterruptedException {
        FileReaderClass read = new FileReaderClass(contex);

        WatchService watchService = FileSystems.getDefault().newWatchService();
        watchedDirectory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        boolean valid;
        do {
            WatchKey watchKey = watchService.take();

            for (WatchEvent event : watchKey.pollEvents()) {
                if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
                    String fileName = event.context().toString();
                    System.out.println("File Created:" + fileName);
                    read.readFile(watchedDirectory + "\\" + fileName);
                    deleteFile(fileName);
                }
            }
            valid = watchKey.reset();

        } while (valid);
    }

    public void readFirstFiles() throws IOException {
        FileReaderClass read = new FileReaderClass(contex);

        File[] files = new File(watchedDirectory.toString()).listFiles();

        for (File file : files) {
            if (file.isFile()) {
                read.readFile(file.getAbsolutePath());
            }
        }
        for (int i = files.length - 1; i > -1; i--) {
            deleteFile(files[i].getName());
        }

    }

    public void deleteFile(String fileName) throws IOException {
        try {

            //add necessary delay, files may be still in use
            Thread.sleep(1000);

            Files.delete(Paths.get(watchedDirectory + "\\" + fileName));
            System.out.println("File Deleted:" + fileName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
