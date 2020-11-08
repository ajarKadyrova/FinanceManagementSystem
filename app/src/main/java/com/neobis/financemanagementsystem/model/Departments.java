package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.SerializedName;

public class Departments {
    @SerializedName("name")
    private String depName;

    public Departments(String depName) {
        this.depName = depName;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }
}
