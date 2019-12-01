package com.automation.silionie.githubPart1;

import com.automation.silionie.base.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class EndpointsTest extends TestBase {

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
    public void postRepotoGithubUserTest() {

        String repoBody = "{" +
            "\"name\":\"Ohpen-Automation-Repo\"," +
            "\"description\":\"An automation repo for Ohpen Testing\"," +
            "\"homepage\":\"https://github.com\"," +
            "\"private\": false," +
            "\"has_issues\": true," +
            "\"has_projects\": true ," +
            "\"has_wiki\": true }";

        Object id = given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(accessToken)
                .when()
                .body(repoBody)
                .post("/user/repos").then()
                .extract()
                .path("id");
        System.out.println(id);
    }

}
