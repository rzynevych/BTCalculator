package com.rz.btcalculator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rz.btcalculator.models.*;
import com.rz.btcalculator.utils.ApplicationData;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ReimbursementCalculationService {

    private final ApplicationData applicationData;

    public ReimbursementCalculationService() {
        applicationData = ApplicationData.getInstance();
    }

    public ReimbursementCalculationResponse calculate(ReimbursementCalculationRequest request) throws JsonProcessingException {
        BigDecimal totalReimbursementAmount = BigDecimal.ZERO;
        BigDecimal dailyAllowance = applicationData.getDailyAllowance();
        BigDecimal mileageAllowance = applicationData.getMileageAllowance();
        BigDecimal mileageReimbursementLimit = applicationData.getMileageReimbursementLimit();
        BigDecimal totalReimbursementLimit = applicationData.getTotalReimbursementLimit();
        Map<String, ReceiptType> receiptTypeMap = applicationData.getReceiptTypes();

        List<TripDay> days = request.getTripDays();
        for (TripDay day : days) {
            if (!day.isDisabled())
                totalReimbursementAmount = totalReimbursementAmount.add(dailyAllowance);
        }

        List<Receipt> receipts = request.getReceipts();
        for (Receipt receipt : receipts) {
            ReceiptType receiptType = receiptTypeMap.get(receipt.getType());
            BigDecimal receiptAmount = receipt.getTotalAmount();
            if (receiptAmount.compareTo(receiptType.getMaxAmount()) <= 0)
                totalReimbursementAmount = totalReimbursementAmount.add(receipt.getTotalAmount());
            else
                totalReimbursementAmount = totalReimbursementAmount.add(receiptType.getMaxAmount());
        }

        BigDecimal mileageReimbursement = mileageAllowance.multiply(BigDecimal.valueOf(request.getDrivenDistance()));
        if (mileageReimbursement.compareTo(mileageReimbursementLimit) > 0)
            mileageReimbursement = mileageReimbursementLimit;

        totalReimbursementAmount = totalReimbursementAmount.add(mileageReimbursement);

        if (totalReimbursementAmount.compareTo(totalReimbursementLimit) > 0)
            totalReimbursementAmount = totalReimbursementLimit;

        ReimbursementCalculationResponse response =
                new ReimbursementCalculationResponse(totalReimbursementAmount, "");

        return response;
    }
}
