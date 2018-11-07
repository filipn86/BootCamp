package pl.coreservices.service;


import com.hazelcast.core.IMap;
import pl.coreservices.model.web.Statistic;
import pl.coreservices.model.web.StatisticsList;


public class StatisticsService {

    public final static String ALL_NAME = "ALL";
    private IMap<String, Statistic> database;

    public StatisticsService(IMap database) {
        this.database = database;
    }

    public StatisticsList getStatistics(String name) {
    	StatisticsList statistics = new StatisticsList();
        if(ALL_NAME.equals(name)) {
        	statistics.setMap(database);
        }
        else {

            StatisticsList temp = new StatisticsList();
        	Integer count = database.get(name).getCount();
        	temp.addHashWithValue(name,count);
            statistics = temp;
        }
        return statistics;
    }

}
