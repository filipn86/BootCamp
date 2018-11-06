package pl.coreservices.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.coreservices.model.web.StatisticsList;
import pl.coreservices.service.StatisticsService;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @RequestMapping("/getStatistics")
    public StatisticsList getStatistics(
            @RequestParam(value = "name", defaultValue = StatisticsService.ALL_NAME) String name) {
        return statisticsService.getStatistics(name);
    }

}
