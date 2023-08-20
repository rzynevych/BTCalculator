package com.rz.btcalculator.models;

import java.math.BigDecimal;

public class Receipt {

    private String type;
    private String amount;
    private String centAmount;

    public Receipt() {}

    public String getType() {
        return type;
    }

    public String getAmount() {
        return amount;
    }

    public String getCentAmount() {
        return centAmount;
    }

    public BigDecimal getTotalAmount() {
        return new BigDecimal(amount + "." + centAmount);
    }
}
