package com.rz.btcalculator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rz.btcalculator.models.ReimbursementCalculationRequest;
import com.rz.btcalculator.models.ReimbursementCalculationResponse;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ReimbursementCalculationServiceTest {

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void simpleTest() throws JsonProcessingException {

        String json = "{\"tripStartDate\":\"2023-08-01\",\"tripEndDate\":\"2023-08-20\",\"tripDays\":[{\"date\":\"01 Aug 2023\",\"disabled\":true},{\"date\":\"02 Aug 2023\",\"disabled\":false},{\"date\":\"03 Aug 2023\",\"disabled\":false},{\"date\":\"04 Aug 2023\",\"disabled\":false},{\"date\":\"05 Aug 2023\",\"disabled\":false},{\"date\":\"06 Aug 2023\",\"disabled\":false},{\"date\":\"07 Aug 2023\",\"disabled\":false},{\"date\":\"08 Aug 2023\",\"disabled\":false},{\"date\":\"09 Aug 2023\",\"disabled\":false},{\"date\":\"10 Aug 2023\",\"disabled\":false},{\"date\":\"11 Aug 2023\",\"disabled\":false},{\"date\":\"12 Aug 2023\",\"disabled\":false},{\"date\":\"13 Aug 2023\",\"disabled\":false},{\"date\":\"14 Aug 2023\",\"disabled\":false},{\"date\":\"15 Aug 2023\",\"disabled\":false},{\"date\":\"16 Aug 2023\",\"disabled\":false},{\"date\":\"17 Aug 2023\",\"disabled\":false},{\"date\":\"18 Aug 2023\",\"disabled\":false},{\"date\":\"19 Aug 2023\",\"disabled\":false},{\"date\":\"20 Aug 2023\",\"disabled\":true}],\"receipts\":[{\"type\":\"plane ticket\",\"amount\":\"780\",\"centAmount\":\"55\"},{\"type\":\"taxi\",\"amount\":\"11\",\"centAmount\":\"75\"},{\"type\":\"hotel\",\"amount\":\"320\",\"centAmount\":\"25\"}],\"drivenDistance\":\"055\"}";

        ReimbursementCalculationService service = new ReimbursementCalculationService();
        ReimbursementCalculationResponse response = service.calculate(objectMapper.readValue(json, ReimbursementCalculationRequest.class));
        assertEquals(0, response.getDecimalReimbursementAmount().compareTo(new BigDecimal("1399.05")));
    }

    @Test
    public void limitsTest() throws JsonProcessingException {

        String json = "{\"tripStartDate\":\"2023-08-01\",\"tripEndDate\":\"2023-08-10\",\"tripDays\":[{\"date\":\"01 Aug 2023\",\"disabled\":false},{\"date\":\"02 Aug 2023\",\"disabled\":false},{\"date\":\"03 Aug 2023\",\"disabled\":false},{\"date\":\"04 Aug 2023\",\"disabled\":false},{\"date\":\"05 Aug 2023\",\"disabled\":false},{\"date\":\"06 Aug 2023\",\"disabled\":false},{\"date\":\"07 Aug 2023\",\"disabled\":false},{\"date\":\"08 Aug 2023\",\"disabled\":false},{\"date\":\"09 Aug 2023\",\"disabled\":false},{\"date\":\"10 Aug 2023\",\"disabled\":false}],\"receipts\":[{\"type\":\"plane ticket\",\"amount\":\"2200\",\"centAmount\":\"00\"},{\"type\":\"bus ticket\",\"amount\":\"120\",\"centAmount\":\"00\"},{\"type\":\"taxi\",\"amount\":\"130\",\"centAmount\":\"00\"},{\"type\":\"hotel\",\"amount\":\"2100\",\"centAmount\":\"00\"}],\"drivenDistance\":\"1200\"}";

        ReimbursementCalculationService service = new ReimbursementCalculationService();
        ReimbursementCalculationResponse response = service.calculate(objectMapper.readValue(json, ReimbursementCalculationRequest.class));
        assertEquals(0, response.getDecimalReimbursementAmount().compareTo(new BigDecimal("4650")));
    }

    @Test
    public void totalLimitTest() throws JsonProcessingException {

        String json = "{\"tripStartDate\":\"2023-08-01\",\"tripEndDate\":\"2023-08-02\",\"tripDays\":[{\"date\":\"01 Aug 2023\",\"disabled\":false},{\"date\":\"02 Aug 2023\",\"disabled\":false}],\"receipts\":[{\"type\":\"plane ticket\",\"amount\":\"2000\",\"centAmount\":\"00\"},{\"type\":\"hotel\",\"amount\":\"2000\",\"centAmount\":\"00\"},{\"type\":\"plane ticket\",\"amount\":\"2000\",\"centAmount\":\"00\"},{\"type\":\"plane ticket\",\"amount\":\"2000\",\"centAmount\":\"00\"},{\"type\":\"hotel\",\"amount\":\"2000\",\"centAmount\":\"00\"},{\"type\":\"hotel\",\"amount\":\"2000\",\"centAmount\":\"00\"}],\"drivenDistance\":0}";

        ReimbursementCalculationService service = new ReimbursementCalculationService();
        ReimbursementCalculationResponse response = service.calculate(objectMapper.readValue(json, ReimbursementCalculationRequest.class));
        assertEquals(0, response.getDecimalReimbursementAmount().compareTo(new BigDecimal("10000")));
    }





}