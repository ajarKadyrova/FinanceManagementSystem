package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

public class Login {
    @Expose
    private String email;
    @Expose
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
