package pl.coreservices.model.web;

import java.util.ArrayList;
import java.util.List;

public class StatisticsList {

    private static StatisticsList instance = null;
    private List <Statistic> statisticList = new ArrayList <>();

    private StatisticsList() {
    }

    public static StatisticsList getInstance() {

        if (instance == null) {
            instance = new StatisticsList();
        }
        return instance;
    }

    public StatisticsList(List <Statistic> statisticList) {
        this.statisticList = statisticList;
    }

    public List <Statistic> getStatisticList() {
        return statisticList;
    }

    public void setStatisticList(List <Statistic> statisticList) {
        this.statisticList = statisticList;
    }

    public void addStatistic(Statistic statistic) {
        statisticList.add(statistic);
    }
    
    public boolean containsStatistic(String hashtag) {
    	for(Statistic stat: statisticList) {
    		if(stat.getName().equals(hashtag)) return true;
    	}
    	return false;
    }
    
    public void incrementStatistic(String hashtag) {
    	for(Statistic stat: statisticList) {
    		if(stat.getName().equals(hashtag)) {
    			stat.setCount(stat.getCount()+1);
    		}
    	}
    }
    
    public void removeStatistic(Statistic statistic) {
        statisticList.remove(statistic);
    }
}
