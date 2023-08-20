package com.rz.btcalculator.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rz.btcalculator.models.ReceiptType;
import com.rz.btcalculator.utils.ApplicationData;
import com.rz.btcalculator.utils.ResponseAdder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet(
        name = "GetReceiptTypesServlet",
        urlPatterns = {"/getReceiptTypes"}
)
public class GetReceiptTypesServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, ReceiptType> receiptTypeMap = ApplicationData.getInstance().getReceiptTypes();

        ResponseAdder.addResponse(resp, objectMapper.writeValueAsString(receiptTypeMap.values()));
    }
}
