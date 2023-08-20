package com.rz.btcalculator.models;

import java.math.BigDecimal;

public class ReceiptType {
    private String type;
    private BigDecimal maxAmount;

    public ReceiptType(String type, BigDecimal maxAmount) {
        this.type = type;
        this.maxAmount = maxAmount;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }
}
