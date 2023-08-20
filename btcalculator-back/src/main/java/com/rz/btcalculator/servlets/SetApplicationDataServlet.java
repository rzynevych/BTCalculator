package com.rz.btcalculator.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rz.btcalculator.models.ApplicationDataDto;
import com.rz.btcalculator.models.ReceiptType;
import com.rz.btcalculator.models.Status;
import com.rz.btcalculator.services.ApplicationDataService;
import com.rz.btcalculator.utils.ApplicationData;
import com.rz.btcalculator.utils.ResponseAdder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(
        name = "SetApplicationDataServlet",
        urlPatterns = {"/setApplicationData"}
)
public class SetApplicationDataServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        BufferedReader reader = req.getReader();
        String json = reader.lines().collect(Collectors.joining("\n"));

        ApplicationDataDto applicationDataDto = objectMapper.readValue(json, ApplicationDataDto.class);
        ApplicationDataService service = new ApplicationDataService();
        ResponseAdder.addResponse(resp, objectMapper.writeValueAsString(service.setApplicationData(applicationDataDto)));
    }
}
