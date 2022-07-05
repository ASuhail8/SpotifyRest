package com.spotify.oauth.api;

import com.spotify.oauth.utils.ConfigLoader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        String envDetails = System.getProperty("baseUrl");
        return  requestSpecBuilder.
                setBaseUri(ConfigLoader.getInstance().getBaseUrl(envDetails)).
                setBasePath("/v1").
                addHeader("Content-Type","application/json").
                log(LogDetail.ALL).
                build();

    }

    public static ResponseSpecification getResponseSpec(){
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        return responseSpecBuilder.
                log(LogDetail.ALL).
                build();
    }

    public static void getConsoleOutput(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        PrintStream stream = new PrintStream(file);
        System.setOut(stream);
    }
}
