package pl.coreservices.model.web;

public class Statistic {
    private String name;
    private Integer count;

    public Statistic(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    public Statistic() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void AddToCounter() {
        count++;
    }
}
