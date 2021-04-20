package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class IncomeWrap {
    @Expose
    private Long count;
    @Expose
    private Object next;
    @Expose
    private Object previous;
    @Expose
    private List<Incomes> results = null;

    public List<Incomes> getIncomes() {
        return results;
    }
}
