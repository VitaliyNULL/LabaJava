package com.laba.labajava.model;

import java.util.Map;

public class AdDetails {

    private String street_name_uk;
    private Integer rooms_count;
    private Integer price;
    private String beautiful_url;
    // Інші поля не потрібні, але їх можна додати

    public String getStreet_name_uk() {
        return street_name_uk;
    }

    public void setStreet_name_uk(String street_name_uk) {
        this.street_name_uk = street_name_uk;
    }

    public Integer getRooms_count() {
        return rooms_count;
    }

    public void setRooms_count(Integer rooms_count) {
        this.rooms_count = rooms_count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBeautiful_url() {
        return beautiful_url;
    }

    public void setBeautiful_url(String beautiful_url) {
        this.beautiful_url = beautiful_url;
    }
}
