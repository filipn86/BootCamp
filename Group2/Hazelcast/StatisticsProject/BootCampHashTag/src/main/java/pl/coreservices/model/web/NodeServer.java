package pl.coreservices.model.web;

import com.hazelcast.config.Config;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;

public class NodeServer {
    private Config config = new Config();
    private HazelcastInstance hzInstance;
    private Map<String, Statistic> map;
    private static NodeServer ourInstance = new NodeServer();

    public static NodeServer getInstance() {
        return ourInstance;
    }

    private NodeServer() {
        config.getSerializationConfig().getSerializerConfigs().add(
                new SerializerConfig().setTypeClass(Statistic.class).setImplementation(new StatisticKryoSerializer())
        );
        config.setInstanceName("name");
        this.hzInstance = Hazelcast.newHazelcastInstance(config);
        this.map = hzInstance.getMap("data");
    }

    public void addMember(String name, Statistic statistic) {
        map.put(name, statistic);
    }

    public void getAll() {
        System.out.println(Hazelcast.getAllHazelcastInstances());
    }
}
