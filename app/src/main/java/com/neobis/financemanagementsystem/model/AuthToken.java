package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

public class AuthToken {

    @Expose
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
