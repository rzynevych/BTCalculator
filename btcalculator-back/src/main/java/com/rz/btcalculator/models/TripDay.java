package com.rz.btcalculator.models;

public class TripDay {
    private String date;
    private boolean disabled;

    public TripDay() {}

    public TripDay(String date, boolean disabled) {
        this.date = date;
        this.disabled = disabled;
    }

    public String getDate() {
        return date;
    }

    public boolean isDisabled() {
        return disabled;
    }
}
