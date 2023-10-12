package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.ErrorMessages.*;
import static apisocialnetwork.Helper.isValid;
import static apisocialnetwork.JSONRequests.*;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.*;

public class CommentsControllerTest extends BaseTestSetup {
    @Test
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

    @Test
    public void showCreatedCommentTest() {

        createAndRegisterUser();

        loginUser();

        createPost();

        createComment();

        Response response = showComment();

        int statusCode = response.getStatusCode();
        String createdCommentID = response.getBody().jsonPath().getString("commentId");

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        assertNotNull(response.getBody().asPrettyString());
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(createdCommentID, COMMENT_ID, ERROR_MESSAGE_COMMENT_ID);

    }
    @Test
    public void editCommentTest() {

        createAndRegisterUser();

        loginUser();

        createPost();

        createComment();

        Response response = editComment();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(responseBody, "", ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

    }
    @Test
    public void likeCommentTest() {

        createAndRegisterUser();

        loginUser();

        createPost();

        createComment();

        Response response = likeComment();

        int statusCode = response.getStatusCode();
        int commentIdFromResponse = response.jsonPath().getInt("commentId");
        int expectedCommentId = Integer.parseInt(COMMENT_ID);

        boolean liked = response.jsonPath().getBoolean("liked");

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(commentIdFromResponse, expectedCommentId, ERROR_MESSAGE_COMMENT_ID);
        assertTrue(liked, ERROR_MESSAGE_LIKED_SHOULD_BE_TRUE);

        System.out.println(response.getBody().asPrettyString());

    }
    @Test
    public void dislikeCommentTest() {

        createAndRegisterUser();

        loginUser();

        createPost();

        createComment();

        Response response = likeComment();
        Response responseDisliked = likeComment();

        int statusCode = responseDisliked.getStatusCode();
        int commentIdFromResponse = responseDisliked.jsonPath().getInt("commentId");
        int expectedCommentId = Integer.parseInt(COMMENT_ID);

        boolean liked = responseDisliked.jsonPath().getBoolean("liked");

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(commentIdFromResponse, expectedCommentId, ERROR_MESSAGE_COMMENT_ID);
        assertFalse(liked, ERROR_MESSAGE_LIKED_SHOULD_BE_FALSE);

        System.out.println(responseDisliked.getBody().asPrettyString());

    }
    @Test
    public void deleteCreatedCommentTest() {

        createAndRegisterUser();

        loginUser();

        createPost();

        createComment();

        Response response = deleteComment();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(responseBody, "", ERROR_MESSAGE_RESPONSE_BODY_EMPTY);
    }
    @Test
    public void deletePosts_TearDown() {

        Response response = deletePost();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asPrettyString();

        System.out.println(responseBody);

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(responseBody, "", ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

    }
}
