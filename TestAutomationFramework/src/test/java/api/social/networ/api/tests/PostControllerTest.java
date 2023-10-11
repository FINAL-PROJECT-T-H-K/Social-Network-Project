package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.ErrorMessages.*;
import static apisocialnetwork.ShowMessages.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.*;


public class PostControllerTest extends BaseTestSetup {
    @Test
    public void createPostSuccessfullyTest() {

        createAndRegisterUser();

        loginUser();

        Response response = createPost();

        int statusCode = response.getStatusCode();
        String postContent = response.getBody().jsonPath().getString("content");

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(postContent, POST_DESCRIPTION, ERROR_MESSAGE_DOES_NOT_MATCH_BODY);

        System.out.println(SHOW_MESSAGE_RESPONSE_BODY+response.getBody().asPrettyString());
        System.out.println(SHOW_MESSAGE_POST_CREATED_AND_POST_ID);

    }

    @Test
    public void getAllPostsTest() {

        Response response = showAllPosts();

        System.out.println(response.asString());

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);

        System.out.println(SHOW_MESSAGE_GET_ALL_POSTS);
    }

    @Test
    public void showAllProfilePosts_Successful() {

        createAndRegisterUser();

        loginUser();

        createPost();

        Response response = showAllProfilePosts();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asPrettyString();

        System.out.println(SHOW_MESSAGE_RESPONSE_BODY + responseBody);

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertTrue(responseBody.length() > 2, ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

        System.out.println(SHOW_MESSAGE_GET_ALL_PROFILE_POSTS);
    }


    @Test
    public void editPostsTest() {

        createAndRegisterUser();

        loginUser();

        createPost();


        Response response = editProfilePost();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertTrue(responseBody.isEmpty(), ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

        System.out.println(SHOW_MESSAGE_EDITED_POST);
    }


    @Test
    public void dislikeProfilePostTest() {


        createAndRegisterUser();

        loginUser();

        createPost();

        Response response = likePost();

        int statusCode = response.getStatusCode();
        boolean liked = response.jsonPath().getBoolean("liked");

        String postIdFromResponse = response.jsonPath().getString("postId");

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertEquals(postIdFromResponse, POST_ID, ERROR_MESSAGE_DOES_NOT_MATCH_BODY);
        assertTrue(liked, ERROR_MESSAGE_LIKED_POST);

        System.out.printf(SHOW_MESSAGE_POST_LIKED);
    }

    @Test
    public void deletePostsTest() {

        createAndRegisterUser();

        loginUser();

        createPost();

        Response response = deletePost();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_STATUS_CODE);
        assertTrue(responseBody.isEmpty(), ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

        System.out.printf(SHOW_MESSAGE_POST_DELETED);
    }
}



