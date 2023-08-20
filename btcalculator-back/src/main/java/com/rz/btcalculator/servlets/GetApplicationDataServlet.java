package com.rz.btcalculator.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rz.btcalculator.models.ApplicationDataDto;
import com.rz.btcalculator.models.ReceiptTypeDto;
import com.rz.btcalculator.models.MoneyAmountDto;
import com.rz.btcalculator.services.ApplicationDataService;
import com.rz.btcalculator.utils.ApplicationData;
import com.rz.btcalculator.utils.ResponseAdder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(
        name = "GetApplicationDataServlet",
        urlPatterns = {"/getApplicationData"}
)
public class GetApplicationDataServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ApplicationDataService service = new ApplicationDataService();
        ResponseAdder.addResponse(resp, objectMapper.writeValueAsString(service.getApplicationData()));
    }
}
