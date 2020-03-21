package com.techstack.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static com.techstack.utils.PropertiesContext.getProperty;

public class ApiSpecification {

    public static RequestSpecification getRestContextPath() throws FileNotFoundException {

        PrintStream printStream = new PrintStream(new FileOutputStream("logging.txt"));

        return new RequestSpecBuilder()
                .setBaseUri(getProperty("site.hostUri"))
                .addQueryParam("key", getProperty("site.hostApiKey"))
                .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                .build();
    }
}
