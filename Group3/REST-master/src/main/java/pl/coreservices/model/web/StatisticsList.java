package pl.coreservices.model.web;

import java.util.HashMap;
import java.util.List;

public class StatisticsList {

    private HashMap<String,Integer> statisticList;


    
    public StatisticsList() {
    	statisticList = new HashMap<String,Integer>();
    }
    

    public HashMap<String,Integer> getStatisticList() {
        return statisticList;
    }

    public void setStatisticList(HashMap<String,Integer> statisticList) {
        this.statisticList = statisticList;
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
    
    public void print() {
    	for (String name: statisticList.keySet()){

            String key =name.toString();
            String value = statisticList.get(name).toString();  
            System.out.println(key + " " + value);  

    	} 
    }
    

}
