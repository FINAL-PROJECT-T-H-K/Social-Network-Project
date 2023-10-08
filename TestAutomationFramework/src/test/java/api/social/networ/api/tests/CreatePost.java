package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.Endpoints.*;
import static apisocialnetwork.JSONRequests.POST_BODY;
import static apisocialnetwork.JSONRequests.PROFILE_POST;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class CreatePost extends BaseTestSetup {
    @BeforeClass
    public void Setup() {
        if (isNull(USER_ID)) {
            RegistrationTest registerUser = new RegistrationTest();
            registerUser.registerUser_Successful();
        }

        if (isNull(COOKIE_VALUE)) {
            AuthenticateUser authenticate = new AuthenticateUser();
            authenticate._02_authenticateAndFetchCookies();
        }
    }

    @Test
    public void _02_createPost() {

        baseURI = BASE_URL + CREATE_POST_ENDPOINT;

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .cookie("JSESSIONID", COOKIE_VALUE)
                .body(POST_BODY)
                .when()
                .log()
                .all()
                .post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        System.out.println(response.getBody().asPrettyString());
        System.out.println("Post was created successfully!");

        POST_ID = response.jsonPath().getString("postId");
        System.out.println("Post ID is " + POST_ID);
    }

    //GET ALL POSTS REQ
    @Test
    public void _03_getAllPosts_Successful() {

        baseURI = BASE_URL + GET_ALL_POSTS_ENDPOINT;

        Response response = given()
                .queryParam("sorted", "true")
                .queryParam("unsorted", "false")
                .when()
                .get(baseURI);

        System.out.println(response.asString());

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));

        System.out.println("Successfully fetched all posts.");
    }


    //NOT WORKING
    @Test
    public void _04_showAllProfilePosts_Successful() {
        if (isNull(POST_ID)) {
            CreatePost createPost = new CreatePost();
            createPost._02_createPost();
        }

        baseURI = GET_PROFILE_POSTS;

        Response response = given()
                .contentType(ContentType.JSON)
                .cookies("JSESSIONID", COOKIE_VALUE)
                .body(PROFILE_POST)
                .when()
                .get(baseURI);

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);

        //ASSERT
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertTrue(responseBody.length() > 2, "Response array is empty");
    }


    @Test
    public void _05_deletePosts_Successful() {
        if (isNull(POST_ID)) {
            CreatePost createPost = new CreatePost();
            createPost._02_createPost();
        }

        baseURI = BASE_URL + DELETE_POSTS;


        Response response = given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .queryParam("postId", POST_ID)
                .when()
                .delete(baseURI);

        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asString());
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
    }
}



