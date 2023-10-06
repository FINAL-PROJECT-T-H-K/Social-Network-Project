package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static apiSocialNetwork.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class FindAllPosts extends BaseTestSetup {

    @Test
    public void getAllPosts_Successful() {
        baseURI = BASE_URL + GET_ALL_POSTS_ENDPOINT;

        Response response = RestAssured.given()
                .queryParam("sorted", "true")
                .queryParam("unsorted", "false")
                .when()
                .get(baseURI);

        System.out.println(response.asString());

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));

        System.out.println("Successfully fetched all posts.");
    }
}
