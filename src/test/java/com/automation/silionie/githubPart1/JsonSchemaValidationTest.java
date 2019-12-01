package com.automation.silionie.githubPart1;

import com.automation.silionie.base.TestBase;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class JsonSchemaValidationTest extends TestBase {

    @Test
    public void validateGithubUserSchemaTest() throws IOException, JSONException, URISyntaxException {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("github_user_schema.json");

        String expectedValue = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8.name());

        String actualValue = given().get("/users/mojombo").asString();

        System.out.println(expectedValue);
        System.out.println(actualValue);

        JSONAssert.assertEquals(expectedValue, actualValue, JSONCompareMode.LENIENT);
    }
}
