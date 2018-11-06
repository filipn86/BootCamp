package pl.coreservices.model.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsList {

    private Map<String,Integer> statisticList;

    public StatisticsList() {
    	statisticList = new HashMap<>();
    }

    public Map<String,Integer> getStatisticList() {
        return statisticList;
    }
    
    public Boolean contains(String s) {
    	return statisticList.containsKey(s);
    }
    
    public void addCount(String s) {
    	statisticList.replace(s,statisticList.get(s) + 1);
    }
    
    public void addHash(String s) {
    	statisticList.put(s, 1);
    }
    
    public void addHashWithValue(String s, Integer v) {
    	statisticList.put(s, v);
    }
}
