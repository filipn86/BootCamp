package com.coreservices.service;

import com.coreservices.database.Database;
import com.coreservices.model.Statistic;
import com.coreservices.model.StatisticsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService {
    public final static String ALL_NAME = "ALL";
    private Database database;

    @Autowired
    public StatisticsService(Database database) {
        this.database = database;
    }

    public StatisticsList getStatistics(String name) {
        List<Statistic> statisticList = new ArrayList<>();

        if (ALL_NAME.equals(name)) {
            return database.getStatisticsList();
        } else {
            return new StatisticsList(statisticList);
        }
    }
}
