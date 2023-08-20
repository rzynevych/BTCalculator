package com.rz.btcalculator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rz.btcalculator.models.ApplicationDataDto;
import com.rz.btcalculator.models.ReceiptType;
import com.rz.btcalculator.models.Status;
import com.rz.btcalculator.utils.ApplicationData;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

public class ApplicationDataServiceTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void changeValuesAndNewReceiptTypeTest() throws JsonProcessingException {
        String json = "{\"dailyAllowance\":{\"intPart\":\"18\",\"centPart\":\"55\"},\"mileageAllowance\":{\"intPart\":\"0\",\"centPart\":\"4\"},\"mileageReimbursementLimit\":{\"intPart\":\"400\",\"centPart\":\"00\"},\"totalReimbursementLimit\":{\"intPart\":\"15000\",\"centPart\":\"00\"},\"receiptTypes\":[{\"name\":\"plane ticket\",\"limit\":{\"intPart\":\"1700\",\"centPart\":\"00\"}},{\"name\":\"bus ticket\",\"limit\":{\"intPart\":\"50\",\"centPart\":\"00\"}},{\"name\":\"taxi\",\"limit\":{\"intPart\":\"70\",\"centPart\":\"00\"}},{\"name\":\"hotel\",\"limit\":{\"intPart\":\"2500\",\"centPart\":\"00\"}},{\"name\":\"new\",\"limit\":{\"intPart\":\"10\",\"centPart\":\"55\"}}]}";
        ApplicationDataDto dto = objectMapper.readValue(json, ApplicationDataDto.class);
        ApplicationDataService service = new ApplicationDataService();
        Status status = service.setApplicationData(dto);
        assertTrue(status.isStatus());
        ApplicationData applicationData = ApplicationData.getInstance();
        Map<String, ReceiptType> receiptTypeMap = applicationData.getReceiptTypes();
        assertEquals(0, receiptTypeMap.get("plane ticket").getMaxAmount().compareTo(new BigDecimal("1700")));
        assertEquals(0, receiptTypeMap.get("bus ticket").getMaxAmount().compareTo(new BigDecimal("50")));
        assertEquals(0, receiptTypeMap.get("taxi").getMaxAmount().compareTo(new BigDecimal("70")));
        assertEquals(0, receiptTypeMap.get("hotel").getMaxAmount().compareTo(new BigDecimal("2500")));
        assertEquals(0, receiptTypeMap.get("new").getMaxAmount().compareTo(new BigDecimal("10.55")));
        assertEquals(0, applicationData.getDailyAllowance().compareTo(new BigDecimal("18.55")));
        assertEquals(0, applicationData.getMileageAllowance().compareTo(new BigDecimal("0.4")));
        assertEquals(0, applicationData.getMileageReimbursementLimit().compareTo(new BigDecimal("400")));
        assertEquals(0, applicationData.getTotalReimbursementLimit().compareTo(new BigDecimal("15000")));
    }

    @Test
    public void removeReceiptTypeTest() throws JsonProcessingException {
        String json = "{\"dailyAllowance\":{\"intPart\":\"15\",\"centPart\":\"00\"},\"mileageAllowance\":{\"intPart\":\"0\",\"centPart\":\"3\"},\"mileageReimbursementLimit\":{\"intPart\":\"300\",\"centPart\":\"00\"},\"totalReimbursementLimit\":{\"intPart\":\"10000\",\"centPart\":\"00\"},\"receiptTypes\":[{\"name\":\"plane ticket\",\"limit\":{\"intPart\":\"2000\",\"centPart\":\"00\"}},{\"name\":\"taxi\",\"limit\":{\"intPart\":\"100\",\"centPart\":\"00\"}},{\"name\":\"hotel\",\"limit\":{\"intPart\":\"2000\",\"centPart\":\"00\"}}]}";
        ApplicationDataDto dto = objectMapper.readValue(json, ApplicationDataDto.class);
        ApplicationDataService service = new ApplicationDataService();
        Status status = service.setApplicationData(dto);
        assertTrue(status.isStatus());
        ApplicationData applicationData = ApplicationData.getInstance();
        Map<String, ReceiptType> receiptTypeMap = applicationData.getReceiptTypes();
        receiptTypeMap.get("bus ticket");
        assertNull(receiptTypeMap.get("bus ticket"));
    }
}