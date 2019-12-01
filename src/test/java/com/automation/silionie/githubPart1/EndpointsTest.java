package com.automation.silionie.githubPart1;

import com.automation.silionie.base.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class EndpointsTest extends TestBase {

    static Integer user_id;

    @Test
    public void getGitHubUsersTest() {
        /**
         * given()
         * set cookies, add auth, adding parameters, setting headers
         * when()
         * GET(), POST, DELETE .. etc
         * then()
         * Validate status code, extract response, extract headers, cookies, extract the response body
         */
        //Validate the status code
        given()
                .when()
                .get("/users")
                .then()
                .log().status()
                .statusCode(200);
    }


    @Test
    public void getGitHubUsersByNameTest() {
        /** We're looking at users with the name Tom.
         */

        //Validate the status code
        Response response = given()
                .param("q", "tom")
                .when()
                .get("/search/users");

        System.out.println(response.prettyPeek());

        response.then().statusCode(200);
    }

    @Ignore
    @Test
    public void patchGithubUserTest() {

        String repoBody = "{" +
            "\"name\":\"evi silioni\"," +
            "\"email\":\"evesino@gmail.com\"," +
            "\"blog\":\"https://github.com/blog\"," +
            "\"company\":\"GitHub\"," +
            "\"location\": \"Athens\"," +
            "\"bio\": \"Once upon a time in Barcelona\"" + "}";

        user_id = given()
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .when()
                .body(repoBody)
                .patch("/user")
                .then().extract()
                .path("id");


        Assert.assertNotNull(user_id);
    }




}
