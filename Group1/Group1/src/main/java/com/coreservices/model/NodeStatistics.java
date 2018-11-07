package com.coreservices.model;

import com.coreservices.model.Serializators.StatisticsSerializer;
import com.hazelcast.config.Config;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class NodeStatistics {

    private Config config;
    private HazelcastInstance instance;
    private IMap <Integer, Statistic> statisticMap;
    private static NodeStatistics nodeStatistics = null;

    public static NodeStatistics getInstance() {
        if (nodeStatistics == null) {
            nodeStatistics = new NodeStatistics();
            return nodeStatistics;
        } else {
            return nodeStatistics;
        }
    }
    private NodeStatistics() {
        config.getSerializationConfig().getSerializerConfigs()
                .add(new SerializerConfig().setTypeClass(Statistic.class)
                        .setImplementation(new StatisticsSerializer()));

        config.setInstanceName("statistics");
        this.instance = Hazelcast.newHazelcastInstance(config);
        this.statisticMap = instance.getMap("statisticMap");
    }
    public void addToStatisticMap(Integer index, Statistic statistics) {
        statisticMap.put(index, statistics);
    }
}
