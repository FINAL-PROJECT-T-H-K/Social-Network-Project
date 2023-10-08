package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.Endpoints.*;
import static apisocialnetwork.JSONRequests.*;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class CreateCommentTests extends BaseTestSetup {

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
            CreatePost createPost = new CreatePost();
            createPost._02_createPost();
        }
    }

    @Test
    public void _01_createComment() {
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
        System.out.println("Status Code: " + statusCode);

        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertEquals(response.getBody().jsonPath().getString("content"), COMMENT_DESCRIPTION);
        System.out.println("Response Body: " + response.getBody().asString());

    }

    @Test
    public void _02_showCreatedComment() {
        if (isNull(COMMENT_ID)) {
            _01_createComment();
        }

        baseURI = SHOW_CREATED_COMMENTS;
        Response response = RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .when()
                .get(baseURI);


        int statusCode = response.getStatusCode();
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertEquals(response.getBody().jsonPath().getString("commentId"), COMMENT_ID);

    }

    @Test
    public void _03_showAllCreatedComment() {

        baseURI = BASE_URL + SHOW_ALL_COMMENTS;

        Response response = RestAssured.given()
                .queryParam("sorted", true)
                .queryParam("unsorted", true)
                .when()
                .get(baseURI);


        int statusCode = response.getStatusCode();
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        ///ONE MORE ASSERT
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));

    }

    @Test
    public void _04_editComment() {
        if (isNull(COMMENT_ID)) {
            _01_createComment();
        }

        baseURI = BASE_URL + EDITED_COMMENT;

        Response response = RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .contentType(ContentType.JSON)
                .when()
                .put(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));

        String responseBody = response.getBody().asString();
        assertEquals(responseBody, "");

    }

    @Test
    public void _05_likedComment() {

        if (isNull(COMMENT_ID)) {
            _01_createComment();
        }
        baseURI = BASE_URL + LIKED_COMMENT;

        Response response = RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .contentType(ContentType.JSON)
                .when()
                .post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));


        //ASSERT FOR COMMENTID
        int commentIdFromResponse = response.jsonPath().getInt("commentId");
        int expectedCommentId = Integer.parseInt(COMMENT_ID);
        assertEquals(commentIdFromResponse, expectedCommentId);

        //EVERY TIME PASSED
        boolean liked = response.jsonPath().getBoolean("liked");
        assertEquals(liked, true);


    }

    @Test
    public void _05_deleteCreatedComment() {
        if (isNull(COMMENT_ID)) {
            _01_createComment();
        }

        baseURI = BASE_URL + DELETE_COMMENT;
        Response response = RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .queryParam("commentId", COMMENT_ID)
                .contentType(ContentType.JSON)
                .when()
                .delete(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));

        String responseBody = response.getBody().asString();

        assertEquals(responseBody, "");
        System.out.println("Response Body: " + responseBody);
    }
}
