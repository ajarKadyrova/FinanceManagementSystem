package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class PostIncome {
    @Expose
    private Integer id;
    @Expose
    private String date;
    @Expose
    private int categoryincome;
    @Expose
    private Float amount;
    @Expose
    private int counterparty;
    @Expose
    private int accounts;
    @Expose
    private int projects;
    @Expose
    private String type;

    @Expose
    private String comment;
    @Expose
    private List<String> tags = null;

    public PostIncome(String date, int categoryincome, Float amount, int counterparty,
                      int accounts, int projects, String type, String comment, List<String> tags) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCategoryincome() {
        return categoryincome;
    }

    public void setCategoryincome(int categoryincome) {
        this.categoryincome = categoryincome;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public int getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(int counterparty) {
        this.counterparty = counterparty;
    }

    public int getAccounts() {
        return accounts;
    }

    public void setAccounts(int accounts) {
        this.accounts = accounts;
    }

    public int getProjects() {
        return projects;
    }

    public void setProjects(int projects) {
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
