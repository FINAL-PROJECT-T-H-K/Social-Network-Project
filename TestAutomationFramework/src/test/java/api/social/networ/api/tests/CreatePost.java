package api.social.networ.api.tests;

import api.base.BaseTestSetup;
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
        String postContentPic = response.getBody().jsonPath().getString("picture");

        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
//        assertEquals(postContent,POST_DESCRIPTION,format("Response body content does not match the expected. Expected %s",POST_DESCRIPTION));
//        assertEquals(postContentPic,POST_DESCRIPTION_PIC,format("Response body content does not match the expected. Expected %s",POST_DESCRIPTION_PIC));

        System.out.println(response.getBody().asPrettyString());
        System.out.println("Post was created successfully!");
        System.out.println("Post ID is " + POST_ID);
    }

    //GET ALL POSTS REQ
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
//        if (isNull(POST_ID)) {
//            CreatePost createPost = new CreatePost();
//            createPost.createPost();
//        }

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
    public void deletePosts_Successful() {
//        if (isNull(POST_ID)) {
//            CreatePost createPost = new CreatePost();
//            createPost.createPost();
//        }

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

    //LIKE / DISLIKE POST
}



