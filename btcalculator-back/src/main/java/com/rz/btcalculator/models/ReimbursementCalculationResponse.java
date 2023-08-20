package com.rz.btcalculator.models;

import java.math.BigDecimal;

public class ReimbursementCalculationResponse {

    BigDecimal decimalReimbursementAmount;
    String totalReimbursementAmount;
    String errorMessage;

    public ReimbursementCalculationResponse(BigDecimal decimalReimbursementAmount, String errorMessage) {
        this.decimalReimbursementAmount = decimalReimbursementAmount;
        this.totalReimbursementAmount = decimalReimbursementAmount.toString();
        this.errorMessage = errorMessage;
    }

    public BigDecimal getDecimalReimbursementAmount() {
        return decimalReimbursementAmount;
    }

    public String getTotalReimbursementAmount() {
        return totalReimbursementAmount;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
