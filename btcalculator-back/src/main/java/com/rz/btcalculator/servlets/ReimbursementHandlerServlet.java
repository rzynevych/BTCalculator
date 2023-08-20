package com.rz.btcalculator.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rz.btcalculator.models.ReimbursementCalculationRequest;
import com.rz.btcalculator.services.ReimbursementCalculationService;
import com.rz.btcalculator.utils.ResponseAdder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(
        name = "ReimbursementHandlerServlet",
        urlPatterns = {"/calculateReimbursement"}
)
public class ReimbursementHandlerServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        BufferedReader reader = req.getReader();
        String json = reader.lines().collect(Collectors.joining("\n"));
        ReimbursementCalculationRequest calculationRequest = objectMapper.readValue(json, ReimbursementCalculationRequest.class);
        ReimbursementCalculationService service = new ReimbursementCalculationService();

        ResponseAdder.addResponse(resp, objectMapper.writeValueAsString(service.calculate(calculationRequest)));
    }

}