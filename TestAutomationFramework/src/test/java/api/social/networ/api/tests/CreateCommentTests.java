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
import static apisocialnetwork.JSONRequests.COMMENT_BODY;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.*;

public class CreateCommentTests extends BaseTestSetup {
    PreconditionLogic preconditionLogic = new PreconditionLogic();
    private static final String ERROR_MESSAGE_COMMENT_ID = format("Incorrect comment ID. Expected %s", COMMENT_ID);
    private static final String ERROR_MESSAGE_LIKED_SHOULD_BE_FALSE = "Expected status should be false for disliked comment";
    private static final String ERROR_MESSAGE_LIKED_SHOULD_BE_TRUE = "Expected status should be true for liked comment";
    private static final String ERROR_MESSAGE_RESPONSE_CONTENT = format("Response body content does not match the expected. Expected %s", COMMENT_DESCRIPTION);
    private static final String ERROR_MESSAGE_STATUS_CODE = format("Incorrect status code. Expected %s.", SC_OK);
    private static final String ERROR_MESSAGE_RESPONSE_BODY_EMPTY = "Response body should be empty";

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
            preconditionLogic.createPost();
        }
    }

    @Test(priority = 1)
    public void createComment() {
        baseURI = BASE_URL + COMMENT_ENDPOINT;

        Response response = RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .contentType(ContentType.JSON)
                .body(COMMENT_BODY)
                .when()
                .post(baseURI);

        COMMENT_ID = response.jsonPath().getString("commentId");

        int statusCode = response.getStatusCode();
        String commentContent = response.getBody().jsonPath().getString("content");

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + response.getBody().asPrettyString());

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(commentContent, COMMENT_DESCRIPTION, ERROR_MESSAGE_RESPONSE_CONTENT);

    }

    @Test(priority = 2)
    public void showCreatedComment() {

        baseURI = SHOW_CREATED_COMMENTS;
        Response response = RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .when()
                .get(baseURI);


        int statusCode = response.getStatusCode();
        String createdCommentID = response.getBody().jsonPath().getString("commentId");

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        assertNotNull(response.getBody().asPrettyString());
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(createdCommentID, COMMENT_ID, ERROR_MESSAGE_COMMENT_ID);

    }

    @Test(priority = 3)
    public void showAllCreatedComment() {

        baseURI = BASE_URL + SHOW_ALL_COMMENTS;

        Response response = RestAssured.given()
                .queryParam("sorted", true)
                .queryParam("unsorted", true)
                .when()
                .get(baseURI);

        int statusCode = response.getStatusCode();

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());

        assertNotNull(response.getBody().asPrettyString());
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);

    }

    @Test(priority = 4)
    public void editComment() {

        baseURI = BASE_URL + EDITED_COMMENT;

        Response response = RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .contentType(ContentType.JSON)
                .when()
                .put(baseURI);

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(responseBody, "", ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

    }

    @Test(priority = 5)
    public void likeComment() {

        baseURI = BASE_URL + LIKED_COMMENT;

        Response response = RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .contentType(ContentType.JSON)
                .when()
                .post(baseURI);

        int statusCode = response.getStatusCode();
        int commentIdFromResponse = response.jsonPath().getInt("commentId");
        int expectedCommentId = Integer.parseInt(COMMENT_ID);

        boolean liked = response.jsonPath().getBoolean("liked");

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(commentIdFromResponse, expectedCommentId, ERROR_MESSAGE_COMMENT_ID);
        assertTrue(liked, ERROR_MESSAGE_LIKED_SHOULD_BE_TRUE);

        System.out.println(response.getBody().asPrettyString());

    }

    @Test(priority = 6)
    public void dislikeComment() {

        baseURI = BASE_URL + LIKED_COMMENT;

        Response response = RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .contentType(ContentType.JSON)
                .when()
                .post(baseURI);

        int statusCode = response.getStatusCode();
        int commentIdFromResponse = response.jsonPath().getInt("commentId");
        int expectedCommentId = Integer.parseInt(COMMENT_ID);

        boolean liked = response.jsonPath().getBoolean("liked");

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(commentIdFromResponse, expectedCommentId, ERROR_MESSAGE_COMMENT_ID);
        assertFalse(liked, ERROR_MESSAGE_LIKED_SHOULD_BE_FALSE);

        System.out.println(response.getBody().asPrettyString());

    }

    @Test(priority = 7)
    public void deleteCreatedComment() {

        baseURI = BASE_URL + DELETE_COMMENT;
        Response response = RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .queryParam("commentId", COMMENT_ID)
                .contentType(ContentType.JSON)
                .when()
                .delete(baseURI);

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(responseBody, "", ERROR_MESSAGE_RESPONSE_BODY_EMPTY);
    }

    @Test(priority = 8)
    public void deletePosts_TearDown() {

        baseURI = BASE_URL + DELETE_POSTS;

        Response response = given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .queryParam("postId", POST_ID)
                .when()
                .delete(baseURI);

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asPrettyString();

        System.out.println(responseBody);

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(responseBody, "", ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

    }
}
