package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.Endpoints.*;
import static apisocialnetwork.ErrorMessages.*;
import static apisocialnetwork.Helper.isValid;
import static apisocialnetwork.JSONRequests.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.*;

public class CommentsManipulationTest extends BaseTestSetup {
    @Test(priority = 1)
    public void createCommentTest() {

        createAndRegisterUser();
        loginUser();
        createPost();

        Response response = createComment();

        int statusCode = response.getStatusCode();
        String commentContent = response.getBody().jsonPath().getString("content");

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + response.getBody().asPrettyString());

        assertTrue(isValid(COMMENT_BODY), ERROR_MESSAGE_BODY_NOT_VALID_JSON);
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(commentContent, COMMENT_DESCRIPTION, ERROR_MESSAGE_RESPONSE_CONTENT);

    }

    @Test(priority = 2)
    public void showCreatedCommentTest() {
        if (isNull(USER_ID)) {
            createAndRegisterUser();
        }
        loginUser();
        if (isNull(POST_ID)) {
            createPost();
        }
        if (isNull(COMMENT_ID)){
            createComment();
        }

        Response response = showComment();

        int statusCode = response.getStatusCode();
        String createdCommentID = response.getBody().jsonPath().getString("commentId");

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        assertNotNull(response.getBody().asPrettyString());
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(createdCommentID, COMMENT_ID, ERROR_MESSAGE_COMMENT_ID);

    }

    @Test(priority = 3)
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

    @Test(priority = 4)
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

    @Test(priority = 5)
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
