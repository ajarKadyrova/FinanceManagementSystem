package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class PostIncome {
    @Expose
    private Long id;
    @Expose
    private String date;
    @Expose
    private Long categoryincome;
    @Expose
    private double amount;
    @Expose
    private Long counterparty;
    @Expose
    private Long accounts;
    @Expose
    private Long projects;
    @Expose
    private String type;
    @Expose
    private String comment;
    @Expose
    private List<String> tags = null;

    public PostIncome(String date, long categoryincome, double amount, long counterparty,
                      long accounts, long projects, String type, String comment) {
        this.date = date;
        this.categoryincome = categoryincome;
        this.amount = amount;
        this.counterparty = counterparty;
        this.accounts = accounts;
        this.projects = projects;
        this.type = type;
        this.comment = comment;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getCategoryincome() {
        return categoryincome;
    }

    public void setCategoryincome(long categoryincome) {
        this.categoryincome = categoryincome;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(long counterparty) {
        this.counterparty = counterparty;
    }

    public Long getAccounts() {
        return accounts;
    }

    public void setAccounts(long accounts) {
        this.accounts = accounts;
    }

    public Long getProjects() {
        return projects;
    }

    public void setProjects(long projects) {
        this.projects = projects;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
