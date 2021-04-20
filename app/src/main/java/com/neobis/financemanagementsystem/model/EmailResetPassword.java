package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

public class EmailResetPassword {

    @Expose
    private String email;

    public EmailResetPassword(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
