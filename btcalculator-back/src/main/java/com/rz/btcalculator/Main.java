package com.rz.btcalculator;

import com.rz.btcalculator.servlets.*;
import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("tomcatBaseDir");

        final Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.getService().addConnector(connector);

        String contextPath = "";
        String docBase = new File("./docBase").getAbsolutePath();

        Context context = tomcat.addContext(contextPath, docBase);


        tomcat.addServlet(contextPath,
                "GetReceiptTypesServlet", new GetReceiptTypesServlet());
        context.addServletMappingDecoded("/getReceiptTypes", "GetReceiptTypesServlet");

        tomcat.addServlet(contextPath,
                "GetApplicationDataServlet", new GetApplicationDataServlet());
        context.addServletMappingDecoded("/getApplicationData", "GetApplicationDataServlet");

        tomcat.addServlet(contextPath,
                "ReimbursementHandlerServlet", new ReimbursementHandlerServlet());
        context.addServletMappingDecoded("/calculateReimbursement", "ReimbursementHandlerServlet");

        tomcat.addServlet(contextPath,
                "SetApplicationDataServlet", new SetApplicationDataServlet());
        context.addServletMappingDecoded("/setApplicationData", "SetApplicationDataServlet");

        tomcat.addServlet(contextPath,
                "RootMappingServlet", new RootMappingServlet());
        context.addServletMappingDecoded("/", "RootMappingServlet");

        Wrapper defaultServlet = context.createWrapper();
        defaultServlet.setName("default");
        defaultServlet.setServletClass("org.apache.catalina.servlets.DefaultServlet");
        defaultServlet.addInitParameter("debug", "0");
        defaultServlet.addInitParameter("listings", "false");
        defaultServlet.setLoadOnStartup(1);
        context.addChild(defaultServlet);
        context.addServletMappingDecoded("/", "default");
        context.addWelcomeFile("index.html");

        tomcat.start();
        tomcat.getServer().await();
    }
}