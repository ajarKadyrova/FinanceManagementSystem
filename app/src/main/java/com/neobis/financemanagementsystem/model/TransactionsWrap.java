package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class TransactionsWrap {
    @Expose
    private Long count;
    @Expose
    private Object next;
    @Expose
    private Object previous;
    @Expose
    private List<Transactions> results = null;

    public Long getCount() {
        return count;
    }

    public Object getNext() {
        return next;
    }

    public Object getPrevious() {
        return previous;
    }

    public List<Transactions> getTransactions() {
        return results;
    }

    public void setIncomes(List<Transactions> results) {
        this.results = results;
    }

}
