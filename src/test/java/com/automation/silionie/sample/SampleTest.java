package com.automation.silionie.sample;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SampleTest {

    @BeforeClass
    public static void init(){
        //set the base url and the port number
        RestAssured.baseURI = "http://localhost";
        RestAssured.port=8085;
        RestAssured.basePath="/example";
    }

    @Test
    public void get(){
        /**
         * given()
         * set cookies, add auth, adding parameters, setting headers
         * when()
         * GET(), POST, DELETE .. etc
         * then()
         * Validate status code, extract response, extract headers, cookies, extract the response body
         */
    }
}
