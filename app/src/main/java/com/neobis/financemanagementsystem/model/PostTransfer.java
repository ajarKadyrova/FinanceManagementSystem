package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class PostTransfer {
    @Expose
    private Long id;
    @Expose
    private String date;
    @Expose
    private double amount;
    @Expose
    private Long accounts;
    @Expose
    private Long send_to;
    @Expose
    private String type;
    @Expose
    private String comment;

    public PostTransfer(String date,Long accounts,  double amount, Long send_to, String type, String comment) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.accounts = accounts;
        this.send_to = send_to;
        this.type = type;
        this.comment = comment;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getAccounts() {
        return accounts;
    }

    public void setAccounts(Long accounts) {
        this.accounts = accounts;
    }

    public Long getSend_to() {
        return send_to;
    }

    public void setSend_to(Long send_to) {
        this.send_to = send_to;
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

}
