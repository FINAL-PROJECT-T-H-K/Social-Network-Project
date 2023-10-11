package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.ErrorMessages.*;
import static apisocialnetwork.ShowMessages.*;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.*;


public class PostManipulationTest extends BaseTestSetup {
    @Test(priority = 1)
    public void createPostSuccessfullyTest() {

        createAndRegisterUser();
        loginUser();

        Response response = createPosts();

        int statusCode = response.getStatusCode();
        String postContent = response.getBody().jsonPath().getString("content");

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(postContent, POST_DESCRIPTION, ERROR_MESSAGE_DOES_NOT_MATCH_BODY);

        System.out.println(SHOW_MESSAGE_RESPONSE_BODY+response.getBody().asPrettyString());
        System.out.println(SHOW_MESSAGE_POST_CREATED_AND_POST_ID);

    }

    @Test(priority = 2)
    public void getAllPostsTest() {

        Response response = showAllPosts();

        System.out.println(response.asString());

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);

        System.out.println(SHOW_MESSAGE_GET_ALL_POSTS);
    }


    @Test(priority = 3)
    public void showAllProfilePosts_Successful() {

        if (isNull(USER_ID)) {
            createAndRegisterUser();
        }
        loginUser();
        if (isNull(POST_ID)) {
            createPosts();
        }
        Response response = showAllProfilePosts();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asPrettyString();

        System.out.println(SHOW_MESSAGE_RESPONSE_BODY + responseBody);

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertTrue(responseBody.length() > 2, ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

        System.out.println(SHOW_MESSAGE_GET_ALL_PROFILE_POSTS);
    }


    @Test(priority = 4)
    public void editPostsTest() {

        if (isNull(USER_ID)) {
            createAndRegisterUser();
        }
        loginUser();
        if (isNull(POST_ID)) {
            createPosts();
        }

        Response response = editProfilePost();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertTrue(responseBody.isEmpty(), ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

        System.out.println(SHOW_MESSAGE_EDITED_POST);
    }


    @Test(priority = 5)
    public void dislikeProfilePostTest() {

        if (isNull(USER_ID)) {
            createAndRegisterUser();
        }
        loginUser();
        if (isNull(POST_ID)) {
            createPosts();
        }

        Response response = likePost();

        int statusCode = response.getStatusCode();
        boolean liked = response.jsonPath().getBoolean("liked");

        String postIdFromResponse = response.jsonPath().getString("postId");

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(postIdFromResponse, POST_ID, ERROR_MESSAGE_DOES_NOT_MATCH_BODY);
        assertTrue(liked, ERROR_MESSAGE_LIKED_POST);

        System.out.printf(SHOW_MESSAGE_POST_LIKED);
    }

    @Test(priority = 6)
    public void deletePostsTest() {

        if (isNull(USER_ID)) {
            createAndRegisterUser();
        }
        loginUser();
        if (isNull(POST_ID)) {
            createPosts();
        }

        Response response = deletePost();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertTrue(responseBody.isEmpty(), ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

        System.out.printf(SHOW_MESSAGE_POST_DELETED);
    }

}



