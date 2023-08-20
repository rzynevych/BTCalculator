package com.rz.btcalculator.utils;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ResponseAdder {
    public static void addResponse(HttpServletResponse resp, String response) throws IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.getOutputStream().print(response);
    }
}
