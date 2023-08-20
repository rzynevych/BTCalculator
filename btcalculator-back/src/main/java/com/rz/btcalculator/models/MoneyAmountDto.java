package com.rz.btcalculator.models;

import java.math.BigDecimal;

public class MoneyAmountDto {

    private String intPart;
    private String centPart;

    public MoneyAmountDto() {}

    public MoneyAmountDto(String intPart, String centPart) {
        this.intPart = intPart;
        this.centPart = centPart;
    }

    public MoneyAmountDto(BigDecimal value) {
        String limit = value.toString();
        if (!limit.contains("."))
            limit = limit + ".00";
        String[] parts = limit.split("\\.");
        this.intPart = parts[0];
        this.centPart = parts[1];
    }

    public String getIntPart() {
        return intPart;
    }

    public String getCentPart() {
        return centPart;
    }

    public BigDecimal toBigDecimal() {
        return new BigDecimal(intPart + "." + centPart);
    }
}
