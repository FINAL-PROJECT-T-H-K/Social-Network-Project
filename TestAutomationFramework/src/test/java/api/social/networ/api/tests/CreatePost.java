package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import apisocialnetwork.PreconditionLogic;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.Endpoints.*;
import static apisocialnetwork.JSONRequests.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.*;


public class CreatePost extends BaseTestSetup {
    PreconditionLogic preconditionLogic = new PreconditionLogic();

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

        if (isNull(POST_ID)) {
            PreconditionLogic preconditionLogic = new PreconditionLogic();
            preconditionLogic.createPost();
        }
    }

    @Test(priority = 1)
    public void createPost() {

        baseURI = BASE_URL + CREATE_POST_ENDPOINT;

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Accept", "*/*")
                .cookie("JSESSIONID", COOKIE_VALUE)
                .body(POST_BODY)
                .when()
                .log()
                .all()
                .post(baseURI);

        POST_ID = response.jsonPath().getString("postId");

        int statusCode = response.getStatusCode();
        String postContent = response.getBody().jsonPath().getString("content");

        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertEquals(postContent, POST_DESCRIPTION, format("Response body content does not match the expected. Expected %s", POST_DESCRIPTION));

        System.out.println(response.getBody().asPrettyString());
        System.out.println("Post was created successfully!");
        System.out.println("Post ID is " + POST_ID);
    }

    @Test(priority = 2)
    public void getAllPosts_Successful() {
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

    @Test(priority = 3)
    public void showAllProfilePosts_Successful() {
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

    @Test(priority = 4)
    public void editProfilePosts_Successful() {
        if (isNull(POST_ID)) {
            CreatePost createPost = new CreatePost();
            createPost.createPost();
        }

        baseURI = BASE_URL + EDIT_POST;

        Response responseBody = RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .queryParam("postId", POST_ID)
                .body(POST_EDIT)
                .when()
                .put(baseURI);


        int statusCode = responseBody.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));

        String response = responseBody.getBody().asString();
        assertTrue(response.isEmpty(), "Response has a non-empty body.");
    }


    @Test(priority = 5)
    public void likeProfilePosts_Successful() {
        if (isNull(POST_ID)) {
            CreatePost createPost = new CreatePost();
            createPost.createPost();
        }
        baseURI = BASE_URL + LIKE_POST;

        Response responseBody = RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .contentType(ContentType.JSON)
                .when()
                .post(baseURI);

        int statusCode = responseBody.getStatusCode();

        //ASSERT
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));

        boolean liked = responseBody.jsonPath().getBoolean("liked");
        assertTrue(liked, "Expected status should be true for liked post");

        String postIdFromResponse = responseBody.jsonPath().getString("postId");
        assertEquals(postIdFromResponse, POST_ID, "Response body's postId is not equal to the variable's postId");
        System.out.printf("Post with id:%s was successful liked! ", POST_ID);


    }

    @Test(priority = 6)
    public void dislikeProfilePosts_Successful() {
        if (isNull(POST_ID)) {
            CreatePost createPost = new CreatePost();
            createPost.createPost();
        }
        baseURI = BASE_URL + LIKE_POST;

        Response responseBody = RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .contentType(ContentType.JSON)
                .when()
                .post(baseURI);

        int statusCode = responseBody.getStatusCode();

        //ASSERT
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));

        boolean liked = responseBody.jsonPath().getBoolean("liked");
        assertFalse(liked, "Expected status should be false for disliked comment");

        String postIdFromResponse = responseBody.jsonPath().getString("postId");
        assertEquals(postIdFromResponse, POST_ID, "Response body's postId is not equal to the variable's postId");
        System.out.printf("Post with id:%s was successful disliked! ", POST_ID);

    }


    @Test(priority = 7)
    public void deletePosts_Successful() {
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



