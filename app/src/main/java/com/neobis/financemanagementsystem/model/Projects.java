package com.neobis.financemanagementsystem.model;

import com.google.gson.annotations.Expose;

public class Projects {
    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private boolean is_archived;

    public Projects(String name) {
        this.name = name;
    }

    public Projects(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_archived() {
        return is_archived;
    }

    public void setIs_archived(boolean is_archived) {
        this.is_archived = is_archived;
    }
}
