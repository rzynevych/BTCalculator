package com.rz.btcalculator.models;

import java.util.List;

public class ApplicationDataDto {
    private MoneyAmountDto dailyAllowance;
    private MoneyAmountDto mileageAllowance;
    private MoneyAmountDto mileageReimbursementLimit;
    private MoneyAmountDto totalReimbursementLimit;

    private List<ReceiptTypeDto> receiptTypes;

    public ApplicationDataDto() {}

    public ApplicationDataDto(MoneyAmountDto dailyAllowance, MoneyAmountDto mileageAllowance,
                              MoneyAmountDto mileageReimbursementLimit, MoneyAmountDto totalReimbursementLimit,
                              List<ReceiptTypeDto> receiptTypes) {
        this.dailyAllowance = dailyAllowance;
        this.mileageAllowance = mileageAllowance;
        this.mileageReimbursementLimit = mileageReimbursementLimit;
        this.totalReimbursementLimit = totalReimbursementLimit;
        this.receiptTypes = receiptTypes;
    }

    public MoneyAmountDto getDailyAllowance() {
        return dailyAllowance;
    }

    public MoneyAmountDto getMileageAllowance() {
        return mileageAllowance;
    }

    public MoneyAmountDto getMileageReimbursementLimit() {
        return mileageReimbursementLimit;
    }

    public MoneyAmountDto getTotalReimbursementLimit() {
        return totalReimbursementLimit;
    }

    public List<ReceiptTypeDto> getReceiptTypes() {
        return receiptTypes;
    }
}
