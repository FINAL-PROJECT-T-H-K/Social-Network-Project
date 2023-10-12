package api.base;

import apisocialnetwork.Utils;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static apisocialnetwork.Constants.*;

import java.util.Locale;
import java.util.logging.Logger;

import static apisocialnetwork.Endpoints.*;
import static apisocialnetwork.JSONRequests.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BaseTestSetup {
    Logger logger = Logger.getLogger("");
    public static final String REQUEST = "/request/";
    public static String USERNAME;
    public static String PASSWORD;

    /**
     * Provided configuration resolve REST Assured issue with a POST request without request body.
     * Missing configuration leads to response status code 415 (Unsupported Media Type)
     */
    @BeforeSuite
    public void setupForUsernameAndPassword() {
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);

        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        USERNAME =Utils.generateFakeServiceUsername();
        PASSWORD = Utils.generateFakeServicePassword();

        logger.info ("Username " + USERNAME);
        logger.info("Password " + PASSWORD);

        UNIQUE_NAME = RandomStringUtils.randomAlphabetic(10);
    }

    public RequestSpecification getApplicationAuthentication() {
        return given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .queryParam("username", USERNAME)
                .queryParam("password", PASSWORD);
    }

    public RequestSpecification getApplicationAuthenticationWithSpecificUser(String username, String password) {
        return given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .queryParam("username", username)
                .queryParam("password", password);
    }

    public void loginUser() {
        baseURI = BASE_URL + AUTHENTICATE_ENDPOINT;

        System.out.println("Using Username: " + USERNAME);
        System.out.println("Using Password: " + PASSWORD);

        ValidatableResponse responseBody = getApplicationAuthentication()
                .when()
                .post(baseURI)
                .then()
                .assertThat()
                .statusCode(302);

        String CookieValue = responseBody.extract().cookies().get("JSESSIONID");
        COOKIE_VALUE = CookieValue;

    }

    public void loginUserWithParams(String username, String password) {
        baseURI = BASE_URL + AUTHENTICATE_ENDPOINT;

        System.out.println("Using Username: " + username);
        System.out.println("Using Password: " + password);

        ValidatableResponse responseBody = getApplicationAuthenticationWithSpecificUser(username,password)
                .when()
                .post(baseURI)
                .then()
                .assertThat()
                .statusCode(302);

        String CookieValue = responseBody.extract().cookies().get("JSESSIONID");
        COOKIE_VALUE = CookieValue;
    }

    public void registerUser(String username, String password) {

        baseURI = BASE_URL + REGISTER_ENDPOINT;

        String uniqueEmailReceiver = Utils.generateRandomEmail();

        String uniqueUser = String.format(REGISTRATION_BODY, password, uniqueEmailReceiver, password, username);

        Response response = RestAssured.given()
                .contentType(APPLICATION_JSON)
                .body(uniqueUser)
                .when()
                .post(baseURI);

        String responseID = response.getBody().asString().split(" ")[6];
        USER_ID = responseID;
        RECEIVER_USER_NAME = username;
        RECEIVER_PASSWORD = password;
    }

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
    }

    public void sendConnectionRequest() {

        SENDER_USER_ID = USER_ID;
        String usernameReceiver = Utils.generateUniqueUsername();
        String password = Utils.generateUniquePassword();
        registerUser(usernameReceiver, password);
        RECEIVER_USER_ID = USER_ID;

        baseURI = BASE_URL + SEND_CONNECTION_REQUEST_ENDPOINT;

        String requestBody = String.format(SEND_CONNECTION_REQ_BODY, RECEIVER_USER_ID, usernameReceiver);

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Accept", "*/*")
                .cookie("JSESSIONID", COOKIE_VALUE)
                .queryParam("principal", USERNAME)
                .body(requestBody)
                .when()
                .log()
                .all()
                .post(baseURI);

    }

    public void showReceivedRequests() {

        baseURI = BASE_URL + CONNECTION_REQUEST_ENDPOINT + USER_ID + REQUEST;

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Accept", "*/*")
                .cookie("JSESSIONID", COOKIE_VALUE)
                .when()
                .log()
                .all()
                .get();

        int id = response.jsonPath().getInt("[0].id");
        CONNECTION_ID = String.valueOf(id);

    }
}