package pl.coreservices.service;

import com.google.common.collect.Lists;

import pl.coreservices.model.web.FileList;
import pl.coreservices.model.web.Statistic;
import pl.coreservices.model.web.StatisticsList;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

public class StatisticsService {

    public final static String ALL_NAME = "ALL";


    public StatisticsList getStatistics(String name) {
        List<Statistic> statistics;
        if(ALL_NAME.equals(name)) {
            statistics = Lists.newArrayList(new Statistic("aaa",2),
                    new Statistic("bbb", 3), new Statistic("ccc", 1));
        }
        else {
            statistics = Lists.newArrayList(new Statistic(name, 2));
        }
        return new StatisticsList(statistics);
    }
    
    
    
	public void getHash() {

		try {
			WatchService watchService = FileSystems.getDefault().newWatchService();
			Path path = Paths.get(
					"C:\\sts-bundle\\sts_workspace\\REST-master\\files");
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
			path.register(watchService, StandardWatchEventKinds.ENTRY_DELETE);
			path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

			while (true) {
				WatchKey key;

				key = watchService.take();

				for (WatchEvent<?> event : key.pollEvents()) {

					String filename = event.context().toString();

				}
				// key.reset(); // ???
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//http://localhost:9190/statistics/getStatistics
	
}
