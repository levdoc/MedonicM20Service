package com.levdoc.m20service.model;

public enum Sex {

    MALE("Мужской"),
    FEMALE("Женский");

    private final String sexTextDisplay;

    Sex(String text) {
        this.sexTextDisplay = text;
    }

    public String getSexTextDisplay() {
        return this.sexTextDisplay;
    }

}
