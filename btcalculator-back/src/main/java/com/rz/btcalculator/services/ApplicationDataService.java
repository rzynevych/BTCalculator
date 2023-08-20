package com.rz.btcalculator.services;

import com.rz.btcalculator.models.*;
import com.rz.btcalculator.utils.ApplicationData;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApplicationDataService {

    private ApplicationData applicationData = ApplicationData.getInstance();

    public ApplicationDataService() {}

    public ApplicationDataDto getApplicationData() {
        List<ReceiptTypeDto> receiptTypes = applicationData.getReceiptTypes().values().stream().map(receiptType ->
                        new ReceiptTypeDto(receiptType.getType(), new MoneyAmountDto(receiptType.getMaxAmount())))
                .collect(Collectors.toList());
        return new ApplicationDataDto(
                new MoneyAmountDto(applicationData.getDailyAllowance()),
                new MoneyAmountDto(applicationData.getMileageAllowance()),
                new MoneyAmountDto(applicationData.getMileageReimbursementLimit()),
                new MoneyAmountDto(applicationData.getTotalReimbursementLimit()),
                receiptTypes
        );
    }

    public Status setApplicationData(ApplicationDataDto applicationDataDto) {

        applicationData.setDailyAllowance(applicationDataDto.getDailyAllowance().toBigDecimal());
        applicationData.setMileageAllowance(applicationDataDto.getMileageAllowance().toBigDecimal());
        applicationData.setMileageReimbursementLimit(applicationDataDto.getMileageReimbursementLimit().toBigDecimal());
        applicationData.setTotalReimbursementLimit(applicationDataDto.getTotalReimbursementLimit().toBigDecimal());

        Map<String, ReceiptType> receiptTypeMap = applicationData.getReceiptTypes();
        List<ReceiptType> receiptTypes = applicationDataDto.getReceiptTypes().stream().map(receiptTypeDto ->
                        new ReceiptType(receiptTypeDto.getName(), receiptTypeDto.getLimit().toBigDecimal()))
                .collect(Collectors.toList());
        receiptTypeMap.clear();
        for (ReceiptType receiptType : receiptTypes) {
            receiptTypeMap.put(receiptType.getType(), receiptType);
        }
        return new Status(true, "");
    }
}
