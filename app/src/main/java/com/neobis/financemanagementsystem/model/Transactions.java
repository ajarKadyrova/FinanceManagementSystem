package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

public class Transactions {
    @Expose
    private Long count;
    @Expose
    private Long id;
    @Expose
    private String date;
    @Expose
    private String categoryincome;
    @Expose
    private String categoryexpense;
    @Expose
    private String counterparty;
    @Expose
    private String type;
    @Expose
    private String accounts;
    @Expose
    private String projects;
    @Expose
    private Long amount;
    @Expose
    private String comment;
    @Expose
    private String send_to;

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

    public String getCategoryIncome() {
        return categoryincome;
    }

    public void setCategoryIncome(String categoryIncome) {
        this.categoryincome = categoryIncome;
    }

    public String getCategoryExpense() {
        return categoryexpense;
    }

    public void setCategoryExpense(String categoryExpense) {
        this.categoryexpense = categoryExpense;
    }

    public String getCounterParty() {
        return counterparty;
    }

    public void setCounterParty(String counterparty) {
        this.counterparty = counterparty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSend_to() {
        return send_to;
    }

    public void setSend_to(String send_to) {
        this.send_to = send_to;
    }
}
