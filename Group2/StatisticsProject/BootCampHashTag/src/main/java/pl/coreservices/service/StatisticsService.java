package pl.coreservices.service;

import pl.coreservices.model.web.StatisticsList;

public class StatisticsService {

    public final static String ALL_NAME = "ALL";

    public StatisticsList getStatistics(String name) {

        if (ALL_NAME.equals(name)) {
            return StatisticsList.getInstance();
        } else {
            throw new UnsupportedOperationException();
        }

    }


}
