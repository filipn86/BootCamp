package pl.coreservices.model.web;

import java.io.Serializable;

public class Statistic implements Serializable {

    private String name;
    private Integer count;

    public Statistic() {
    }

    public static class Builder {

        private String name = null;
        private Integer count = null;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder count(Integer count) {
            this.count = count;
            return this;
        }

        public Statistic build() {
            return new Statistic(this);
        }

    }

    private Statistic(Builder builder) {
        this.name = builder.name;
        this.count = builder.count;
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


    @Override
    public String toString() {
        return "Statistic{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
