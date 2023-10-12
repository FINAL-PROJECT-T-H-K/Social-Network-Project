package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import apisocialnetwork.Utils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.Endpoints.*;
import static apisocialnetwork.ErrorMessages.*;
import static apisocialnetwork.JSONRequests.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.*;


public class CreatePost extends BaseTestSetup {

    public static final String ERROR_MESSAGE_DOES_NOT_MATCH_BODY = format("Response body content does not match the expected. Expected %s", POST_DESCRIPTION);
    public static final String ERROR_MESSAGE_EMPTY_BODY = "Response array is empty";
    public static final String ERROR_MESSAGE_FOR_NOT_EQUAL_POST = "Response body's postId is not equal to the variable's postId";
    public static final String ERROR_MESSAGE_FOR_NOT_EQUAL_POST_ID = ERROR_MESSAGE_FOR_NOT_EQUAL_POST;
    BaseTestSetup baseTestSetup = new BaseTestSetup();

    @BeforeMethod
    public void Setup() {

        USERNAME = Utils.generateUniqueUsername();
        PASSWORD = Utils.generateUniquePassword();
        baseTestSetup.registerUser(USERNAME,PASSWORD);
    }

    @Test
    public void createPost() {

        baseTestSetup.loginUser();

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

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(postContent, POST_DESCRIPTION, ERROR_MESSAGE_DOES_NOT_MATCH_BODY);

        System.out.println(response.getBody().asPrettyString());
        System.out.println("Post was created successfully!");
        System.out.println("Post ID is " + POST_ID);
    }

    @Test//(priority = 2)
    public void getAllPosts_Successful() {

        baseURI = BASE_URL + GET_ALL_POSTS_ENDPOINT;

        Response response = given()
                .queryParam("sorted", "true")
                .queryParam("unsorted", "false")
                .when()
                .get(baseURI);

        System.out.println(response.asString());

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);

        System.out.println("Successfully fetched all posts.");
    }

    @Test//(priority = 3)
    public void showAllProfilePosts_Successful() {

        baseTestSetup.loginUser();
        baseTestSetup.createPost();

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
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertTrue(responseBody.length() > 2, ERROR_MESSAGE_EMPTY_BODY);
    }

    @Test//(priority = 4)
    public void editProfilePosts_Successful() {

        baseTestSetup.loginUser();
        baseTestSetup.createPost();

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
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);

        String response = responseBody.getBody().asString();
        assertTrue(response.isEmpty(), RESPONSE_HAS_A_NON_EMPTY_BODY);
    }


    @Test//(priority = 5)
    public void likeProfilePosts_Successful() {

        baseTestSetup.loginUser();
        baseTestSetup.createPost();

        baseURI = BASE_URL + LIKE_POST;

        Response responseBody = RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .contentType(ContentType.JSON)
                .when()
                .post(baseURI);

        int statusCode = responseBody.getStatusCode();

        //ASSERT
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);

        boolean liked = responseBody.jsonPath().getBoolean("liked");
        assertTrue(liked, ERROR_MESSAGE_LIKED_POST);

        String postIdFromResponse = responseBody.jsonPath().getString("postId");
        assertEquals(postIdFromResponse, POST_ID, ERROR_MESSAGE_DOES_NOT_MATCH_BODY);
        System.out.printf("Post with id:%s was successful liked! ", POST_ID);


    }


    @Test//(priority = 7)
    public void deletePosts_Successful() {

        baseTestSetup.loginUser();
        baseTestSetup.createPost();

        baseURI = BASE_URL + DELETE_POSTS;
        Response response = given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .queryParam("postId", POST_ID)
                .when()
                .delete(baseURI);

        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asString());
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
    }
}



