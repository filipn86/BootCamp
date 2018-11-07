package com.coreservices.web;

import com.coreservices.model.StatisticsList;
import com.coreservices.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    @RequestMapping("/getStatistics")
    public StatisticsList getStatistics(@RequestParam(value = "name", defaultValue = StatisticsService.ALL_NAME) String name) {
        return statisticsService.getStatistics(name);
    }
}
