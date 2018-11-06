package pl.coreservices.service;


import pl.coreservices.model.web.StatisticsList;
import pl.coreservices.model.web.StatisticsListEnum;


public class StatisticsService {

    public final static String ALL_NAME = "ALL";


    public StatisticsList getStatistics(String name) {
    	StatisticsList statistics;
        if(ALL_NAME.equals(name)) {
        	statistics = StatisticsListEnum.statlist; 
        }
        else {
            StatisticsList temp = new StatisticsList();
        	Integer count = StatisticsListEnum.statlist.getStatisticList().get(name);
        	temp.addHashWithValue(name,count);
            statistics = temp;
        }
        return statistics;
    }

}
