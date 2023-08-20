package com.rz.btcalculator.models;

import java.util.List;

public class ReimbursementCalculationRequest {
    private String tripStartDate;
    private String tripEndDate;
    private List<TripDay> tripDays;
    private List<Receipt> receipts;
    private double drivenDistance;

    public ReimbursementCalculationRequest() {}

    public String getTripStartDate() {
        return tripStartDate;
    }

    public String getTripEndDate() {
        return tripEndDate;
    }

    public List<TripDay> getTripDays() {
        return tripDays;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public double getDrivenDistance() {
        return drivenDistance;
    }
}
