package pl.coreservices.configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.coreservices.model.web.Statistic;
import pl.coreservices.model.web.StatisticKryoSerializer;
import pl.coreservices.service.Receiver;
import pl.coreservices.service.Sender;
import pl.coreservices.service.StatisticsService;

import javax.jms.ConnectionFactory;

@Configuration
@ComponentScan(basePackages = {"pl.coreservices"})
public class WebAppConfiguration {



    @Bean
    public StatisticsService statisticsService(IMap<String, Statistic> database) {
        return new StatisticsService(database);
    }

    @Bean
    public IMap<String, Statistic> database() {
        Config config = new Config();
        config.getSerializationConfig().getSerializerConfigs().add(
                new SerializerConfig().
                        setTypeClass(Statistic.class).
                        setImplementation(new StatisticKryoSerializer()));
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(config);

        IMap<String, Statistic> map = hzInstance.getMap("database");
        return map;
    }
}
