package com.rz.btcalculator.utils;

import com.rz.btcalculator.models.ReceiptType;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class ApplicationData {
    private static ApplicationData instance = null;
    private final Map<String, ReceiptType> receiptTypes;
    private final AtomicReference<BigDecimal> dailyAllowance;
    private final AtomicReference<BigDecimal> mileageAllowance;
    private final AtomicReference<BigDecimal> mileageReimbursementLimit;
    private final AtomicReference<BigDecimal> totalReimbursementLimit;


    private ApplicationData() {

        receiptTypes = new ConcurrentHashMap<>();
        receiptTypes.put("taxi", new ReceiptType("taxi", BigDecimal.valueOf(100)));
        receiptTypes.put("hotel", new ReceiptType("hotel", BigDecimal.valueOf(2000)));
        receiptTypes.put("plane ticket", new ReceiptType("plane ticket", BigDecimal.valueOf(2000)));
        receiptTypes.put("bus ticket", new ReceiptType("bus ticket", BigDecimal.valueOf(100)));

        dailyAllowance = new AtomicReference<>(BigDecimal.valueOf(15));
        mileageAllowance = new AtomicReference<>(new BigDecimal("0.3"));
        mileageReimbursementLimit = new AtomicReference<>(BigDecimal.valueOf(300));
        totalReimbursementLimit = new AtomicReference<>(BigDecimal.valueOf(10000));
    }

    public static ApplicationData getInstance() {
        if (instance == null)
            instance = new ApplicationData();
        return instance;
    }

    public Map<String, ReceiptType> getReceiptTypes() {
        return receiptTypes;
    }

    public BigDecimal getDailyAllowance() {
        return dailyAllowance.get();
    }

    public void setDailyAllowance(BigDecimal dailyAllowance) {
        this.dailyAllowance.set(dailyAllowance);
    }

    public BigDecimal getMileageAllowance() {
        return mileageAllowance.get();
    }

    public void setMileageAllowance(BigDecimal mileageAllowance) {
        this.mileageAllowance.set(mileageAllowance);
    }

    public BigDecimal getMileageReimbursementLimit() {
        return mileageReimbursementLimit.get();
    }

    public void setMileageReimbursementLimit(BigDecimal mileageReimbursementLimit) {
        this.mileageReimbursementLimit.set(mileageReimbursementLimit);
    }

    public BigDecimal getTotalReimbursementLimit() {
        return totalReimbursementLimit.get();
    }

    public void setTotalReimbursementLimit(BigDecimal totalReimbursementLimit) {
        this.totalReimbursementLimit.set(totalReimbursementLimit);
    }
}
