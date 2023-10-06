package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static apiSocialNetwork.Constants.*;
import static apiSocialNetwork.Constants.ID;
import static apiSocialNetwork.Endpoints.*;
import static apiSocialNetwork.JSONRequests.REGISTRATION_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class AuthenticateUser extends BaseTestSetup {

    @BeforeEach
    public void authentication() {
        PreemptiveBasicAuthScheme preemptiveBasicAuthScheme = new PreemptiveBasicAuthScheme();
        preemptiveBasicAuthScheme.setUserName(USERNAME);
        preemptiveBasicAuthScheme.setPassword(PASSWORD);
        RestAssured.authentication = preemptiveBasicAuthScheme;
    }
    @Test
    public void _01_registerUser_Successful() {

        baseURI = BASE_URL + REGISTER_ENDPOINT;

        String uniqueUser = String.format(REGISTRATION_BODY,PASSWORD,USERNAME);

        Response response = RestAssured.given()
                .contentType(APPLICATION_JSON)
                .body(uniqueUser)
                .when()
                .post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));

        String responseUsername = response.getBody().asString().split(" ")[3];
        assertEquals(responseUsername, USERNAME, "Username does not match the expected username");

        String responseID = response.getBody().asString().split(" ")[6];
        ID = responseID;

        assertEquals(responseID, ID, "User Id does not match the expected ID");

        System.out.printf("Username with name '%s' was successfully created.", USERNAME );

    }

    @Test
    public static void _02_authenticateAndFetchCookies() {

        RestAssured.baseURI = BASE_URL;
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(USERNAME);
        authScheme.setPassword(PASSWORD);
        RestAssured.authentication = authScheme;

        System.out.println("Using Username: " + USERNAME);
        System.out.println("Using Password: " + PASSWORD);

        ValidatableResponse responseBody = RestAssured
                .given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .queryParam("username", USERNAME)
                .queryParam("password", PASSWORD)
                .when()
                .post(AUTHENTICATE_ENDPOINT)
                .then()
                .assertThat()
                .statusCode(302);

        int statusCode = responseBody.extract().statusCode();
        String CookieValue = responseBody.extract().cookies().get("JSESSIONID");
        COOKIE_VALUE = CookieValue;
                Assert.assertFalse(CookieValue.isEmpty(), "Cookie value is not present");
//                assertEquals(CookieValue.isEmpty(),);
        System.out.println("Cookie value is: " + CookieValue);
        System.out.println("Status code is: " + statusCode);

    }
}

