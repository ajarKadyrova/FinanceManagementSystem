package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

public class CategoryOfIncome {

    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private int total;

    public CategoryOfIncome(String name) {
        this.name = name;
    }

    public CategoryOfIncome(Long id, String name, int total) {
        this.id = id;
        this.name = name;
        this.total = total;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
