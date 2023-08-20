package com.rz.btcalculator.models;

public class Status {

    boolean status;
    String errorMessage;

    public Status() {}

    public Status(boolean status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public boolean isStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
