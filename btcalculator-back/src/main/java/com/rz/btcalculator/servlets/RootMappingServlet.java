package com.rz.btcalculator.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@WebServlet(
        name = "RootMappingServlet",
        urlPatterns = {"/"}
)
public class RootMappingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        InputStream inputStream = new FileInputStream("index.html");
        IOUtils.copy(inputStream, resp.getOutputStream());
    }
}