package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

public class FilterData {

    @Expose
    private long type;
    @Expose
    private long counterparty;
    @Expose
    private long project;
    @Expose
    private long categoryincome;
    @Expose
    private long categoryexpense;
    @Expose
    private String start_date;
    @Expose
    private String end_date;
    @Expose
    private long account;


    public FilterData(long type, long counterparty, long project, long categoryincome, long categoryexpense, String start_date, String end_date, long account) {
        this.type = type;
        this.counterparty = counterparty;
        this.project = project;
        this.categoryincome = categoryincome;
        this.categoryexpense = categoryexpense;
        this.start_date = start_date;
        this.end_date = end_date;
        this.account = account;
    }

    public long getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(int counterparty) {
        this.counterparty = counterparty;
    }

    public long getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public long getCategoryincome() {
        return categoryincome;
    }

    public void setCategoryincome(int categoryincome) {
        this.categoryincome = categoryincome;
    }

    public long getCategoryexpense() {
        return categoryexpense;
    }

    public void setCategoryexpense(int categoryexpense) {
        this.categoryexpense = categoryexpense;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public long getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }
}
