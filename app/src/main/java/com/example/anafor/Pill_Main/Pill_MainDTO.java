package com.example.anafor.Pill_Main;

public class Pill_MainDTO {

    private String name, date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Pill_MainDTO(String name, String date) {
        this.name = name;
        this.date = date;
    }
}

