package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Transfer {

    @Expose
    private Integer id;
    @Expose
    private String date;
    @Expose
    private String accounts;
    @Expose
    private Integer amount;
    @Expose
    private String send_to;
    @Expose
    private String type;
    @Expose
    private String comment;

    public Transfer(Integer id, String date, String accounts, Integer amount, String send_to, String type, String comment) {
        this.id = id;
        this.date = date;
        this.accounts = accounts;
        this.amount = amount;
        this.send_to = send_to;
        this.type = type;
        this.comment = comment;
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

    public String getAccounts() {
        return accounts;
    }

    public void setAccounts(String accounts) {
        this.accounts = accounts;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getSend_to() {
        return send_to;
    }

    public void setSend_to(String send_to) {
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
