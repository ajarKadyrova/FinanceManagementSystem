package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Incomes {
    @Expose
    private Integer id;
    @Expose
    private String date;
    @Expose
    private String categoryincome;
    @Expose
    private Integer amount;
    @Expose
    private String counterparty;
    @Expose
    private String accounts;
    @Expose
    private String projects;
    @Expose
    private String type;
    @Expose
    private String comment;
    @Expose
    private List<String> tags = null;

    public Incomes(String date, String categoryincome, Integer amount,
                   String counterparty, String accounts, String projects, String type,
                   String comment, List<String> tags) {
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

    public String getCategoryIncome() {
        return categoryincome;
    }

    public void setCategoryIncome(String categoryIncome) {
        this.categoryincome = categoryIncome;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
    }

    public String getAccounts() {
        return accounts;
    }

    public void setAccounts(String accounts) {
        this.accounts = accounts;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
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
