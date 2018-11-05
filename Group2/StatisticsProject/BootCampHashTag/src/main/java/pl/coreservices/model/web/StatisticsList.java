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
    public void removeStatistic(Statistic statistic) {
        statisticList.remove(statistic);
    }
}
