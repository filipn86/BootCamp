package com.coreservices.database;

import com.coreservices.model.Statistic;
import com.coreservices.model.StatisticsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Database {
    private StatisticsList statisticsList;

    @Autowired
    public Database() {
        statisticsList = new StatisticsList();
    }

    public void add(Statistic statistic) {
        if(statisticsList.getStatisticList().contains(statistic)) {
            int i = statisticsList.getStatisticList().indexOf(statistic);
            int count = statisticsList.getStatisticList().get(i).getCount();
            statisticsList.getStatisticList().get(i).setCount(count + 1);
        } else {
            statisticsList.getStatisticList().add(statistic);
        }
    }

    public StatisticsList getStatisticsList() {
        return statisticsList;
    }
}
