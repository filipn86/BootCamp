package com.coreservices.model;

import java.util.ArrayList;
import java.util.List;

public class StatisticsList {
    private List<Statistic> statisticList;

    public StatisticsList() {
        this.statisticList = new ArrayList<>();
    }

    public StatisticsList(List<Statistic> statisticList) {
        this.statisticList = statisticList;
    }

    public List<Statistic> getStatisticList() {
        return statisticList;
    }
}
