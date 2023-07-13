package com.levdoc.m20service.model;

public enum Role {

    ADMIN("Администратор"),
    DOCTOR("Врач"),
    LABWORKER("Лаборант");

    private final String roleTextDisplay;

    Role(String text) {
        this.roleTextDisplay = text;
    }

    public String getRoleTextDisplay() {
        return this.roleTextDisplay;
    }

}
