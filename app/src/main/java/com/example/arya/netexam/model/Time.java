package com.example.arya.netexam.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Time {
    @SerializedName("timeInMinutes")
    @Expose
    private int timeInMinutes;

    public Time(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }
}
