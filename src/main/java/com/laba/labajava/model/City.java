package com.laba.labajava.model;

public class City {
    private Integer cityID;
    private String name;
    // Інші поля можна додати при потребі

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
