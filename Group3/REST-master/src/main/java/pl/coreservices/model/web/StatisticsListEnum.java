package pl.coreservices.model.web;

import java.util.List;

//This is class, but called Enum
public class StatisticsListEnum {
	
	public static StatisticsList statlist;
	
	// static variable single_instance of type Singleton 
    private static StatisticsListEnum single_instance = null; 
  
  
    // private constructor restricted to this class itself 
    public StatisticsListEnum() 
    { 
    	statlist = new StatisticsList();
    } 
  
    // static method to create instance of Singleton class 
    public static StatisticsListEnum getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new StatisticsListEnum(); 
  
        return single_instance; 
    } 
	

}
