package com.automation.silionie.base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class TestBase {

    protected static String accessToken;

    private static String CLIENTID;
    private static String CLIENTSECRET;

    private InputStream input = null;
    private static final String FILENAME_PROP = "application.properties";

    @BeforeClass
    public void init() throws IOException {

        RestAssured.baseURI = "https://api.github.com";

        Properties props = new Properties();
        input = getClass().getClassLoader().getResourceAsStream(FILENAME_PROP);
        props.load(input);

        CLIENTID = props.getProperty("clientId");
        CLIENTSECRET = props.getProperty("clientSecret");

        accessToken = RestAssured.given()
                .accept(ContentType.JSON)
                .auth()
                .preemptive()
                .basic(CLIENTID, CLIENTSECRET)
                .when().post("https://github.com/login/oauth/token").then()
                .extract()
                .path("access_token");
    }
}