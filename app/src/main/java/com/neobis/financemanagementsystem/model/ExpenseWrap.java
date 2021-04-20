package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ExpenseWrap {
    @Expose
    private Long count;
    @Expose
    private Object next;
    @Expose
    private Object previous;
    @Expose
    private List<Expenses> results = null;

    public Long getCount() {
        return count;
    }

    public Object getNext() {
        return next;
    }

    public Object getPrevious() {
        return previous;
    }

    public List<Expenses> getExpenses() {
        return results;
    }

    public void setExpenses(List<Expenses> results) {
        this.results = results;
    }

}
