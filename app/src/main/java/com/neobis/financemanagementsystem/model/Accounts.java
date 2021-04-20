package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

public class Accounts {

    @Expose
    private Long id;
    @Expose
    private String type;
    @Expose
    private Long amount;

    public Accounts(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
