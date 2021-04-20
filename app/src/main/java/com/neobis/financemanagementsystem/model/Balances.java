package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

public class Balances {
    @Expose
    private Long totalincome;
    @Expose
    private Long totalexpense;
    @Expose
    private Long totalamount;

    public Balances(Long totalincome, Long totalexpense, Long totalamount) {
        this.totalincome = totalincome;
        this.totalexpense = totalexpense;
        this.totalamount = totalamount;
    }

    public Long getTotalincome() {
        return totalincome;
    }

    public Long getTotalexpense() {
        return totalexpense;
    }

    public Long getTotalamount() {
        return totalamount;
    }
}
