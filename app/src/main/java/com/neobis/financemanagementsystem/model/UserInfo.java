package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

public class UserInfo {

    @Expose
    private Long id;
    @Expose
    private String email;
    @Expose
    private String name;
    @Expose
    private String surname;
    @Expose
    private String assigning;
    @Expose
    private String dateCreated;

    public UserInfo(String email, String name, String surname, String assigning, String dateCreated, String phone) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.assigning = assigning;
        this.dateCreated = dateCreated;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAssigning() {
        return assigning;
    }

    public void setAssigning(String assigning) {
        this.assigning = assigning;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Expose
    private String phone;
}
