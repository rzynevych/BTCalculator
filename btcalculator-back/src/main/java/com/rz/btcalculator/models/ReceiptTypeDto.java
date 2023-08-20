package com.rz.btcalculator.models;

public class ReceiptTypeDto {
    private String name;
    private MoneyAmountDto limit;

    public ReceiptTypeDto() {}

    public ReceiptTypeDto(String name, MoneyAmountDto limit) {
        this.name = name;
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public MoneyAmountDto getLimit() {
        return limit;
    }
}
