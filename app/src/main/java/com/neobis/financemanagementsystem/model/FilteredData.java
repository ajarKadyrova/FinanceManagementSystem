package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

public class FilteredData {

    @Expose
    private long type;
    @Expose
    private String counterparty;
    @Expose
    private String project;
    @Expose
    private String categoryincome;
    @Expose
    private String categoryexpense;
    @Expose
    private String start_date;
    @Expose
    private String end_date;
    @Expose
    private String account;

    public FilteredData(long type, String counterparty, String project, String categoryincome, String categoryexpense, String start_date, String end_date, String account) {
        this.type = type;
        this.counterparty = counterparty;
        this.project = project;
        this.categoryincome = categoryincome;
        this.categoryexpense = categoryexpense;
        this.start_date = start_date;
        this.end_date = end_date;
        this.account = account;
    }

}
