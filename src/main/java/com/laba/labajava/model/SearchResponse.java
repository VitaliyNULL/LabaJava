package com.laba.labajava.model;

import java.util.List;

public class SearchResponse {
    private Integer count;
    private List<Integer> items;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Integer> getItems() {
        return items;
    }

    public void setItems(List<Integer> items) {
        this.items = items;
    }
}
