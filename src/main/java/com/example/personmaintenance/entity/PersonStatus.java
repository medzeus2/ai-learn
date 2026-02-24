package com.example.personmaintenance.entity;

public enum PersonStatus {
    ENABLED("启用"),
    DISABLED("禁用");

    private final String label;

    PersonStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
