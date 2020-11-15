package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

public class CounterPartner {

    @Expose
    private Long id;
    @Expose
    private String name;

    public CounterPartner(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
