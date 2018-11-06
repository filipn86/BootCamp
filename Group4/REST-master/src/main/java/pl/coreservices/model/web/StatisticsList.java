package pl.coreservices.model.web;

import java.util.List;

public class StatisticsList {

    private List<Statistic> statisticList;

    public StatisticsList(List<Statistic> statisticList) {
        this.statisticList = statisticList;
    }

    public List<Statistic> getStatisticList() {
        return statisticList;
    }

    public void setStatisticList(List<Statistic> statisticList) {
        this.statisticList = statisticList;
    }
}
