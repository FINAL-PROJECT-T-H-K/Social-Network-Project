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


    //NOT WORKING
    @Test
    public void _01_createComment() {
        baseURI = BASE_URL + COMMENT_ENDPOINT;

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(COMMENT_BODY)
                .when()
                .post(baseURI);

        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);

        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        System.out.println(response.getBody().asPrettyString());
    }
}