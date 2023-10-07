package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static apisocialnetwork.Endpoints.*;
import static apisocialnetwork.JSONRequests.POST_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;


public class CreatePost extends BaseTestSetup {
    @BeforeClass
    public void cookieSetup() {
        if (isNull(COOKIE_VALUE)) {
            AuthenticateUser auth = new AuthenticateUser();
            auth._02_authenticateAndFetchCookies();
        }
    }

    @Test
    public static void _02_createPost(){

        baseURI= BASE_URL+CREATE_POST_ENDPOINT;

        Response response = RestAssured
                .given()
                .header("Content-Type","application/json")
                .header("Accept","*/*")
                .cookie("JSESSIONID",COOKIE_VALUE)
                .body(POST_BODY)
                .when()
                .log()
                .all()
                .post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        System.out.println("Post was created successfully!");

    }
    //GET ALL POSTS REQ
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


