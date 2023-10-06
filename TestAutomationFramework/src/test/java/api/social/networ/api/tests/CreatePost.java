package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import org.junit.jupiter.api.BeforeEach;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;

import static apiSocialNetwork.Constants.*;
import static apiSocialNetwork.Endpoints.*;
import static apiSocialNetwork.JSONRequests.POST_BODY;
import static apiSocialNetwork.JSONRequests.REGISTRATION_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;


public class CreatePost extends BaseTestSetup {

    @BeforeEach
    public void authentication() {
        PreemptiveBasicAuthScheme preemptiveBasicAuthScheme = new PreemptiveBasicAuthScheme();
        preemptiveBasicAuthScheme.setUserName(USERNAME);
        preemptiveBasicAuthScheme.setPassword(PASSWORD);
        RestAssured.authentication = preemptiveBasicAuthScheme;
    }

    @Test
    public static void _03_createPost() {
        baseURI = BASE_URL + CREATE_POST_ENDPOINT;

        String kuki = "JSESSIONID="+COOKIE_VALUE;

        Response response = RestAssured
                .given()
                .header("Content-Type","application/json")
                .header("Accept","*/*")
                .cookie(COOKIE_VALUE)
                .body(POST_BODY)
                .when()
                .log()
                .all()
                .post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        System.out.println("Post was created successfully!");
    }


}

