package api.base;

import apisocialnetwork.Utils;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeSuite;
import static apisocialnetwork.Constants.*;

import java.util.Locale;

import static apisocialnetwork.Endpoints.*;
import static apisocialnetwork.JSONRequests.POST_BODY;
import static apisocialnetwork.JSONRequests.REGISTRATION_BODY;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BaseTestSetup {
    public static String USERNAME;
    public static String PASSWORD;

    /**
     * Provided configuration resolve REST Assured issue with a POST request without request body.
     * Missing configuration leads to response status code 415 (Unsupported Media Type)
     */
    @BeforeSuite
    public void setup() {
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);

        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String randomUsername = fakeValuesService.bothify("Username?????");
        String randomPassword = fakeValuesService.bothify("Password?????");

        USERNAME = randomUsername;
        PASSWORD = randomPassword;

        UNIQUE_NAME = RandomStringUtils.randomAlphabetic(10);
    }

    public RequestSpecification getApplicationAuthentication() {
        return given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .queryParam("username", USERNAME)
                .queryParam("password", PASSWORD);
    }

    public void registerUser(String username, String password) {

        baseURI = BASE_URL + REGISTER_ENDPOINT;

        String uniqueEmailReceiver = Utils.generateRandomEmail();

        String uniqueUser = String.format(REGISTRATION_BODY, password, uniqueEmailReceiver, password,username);

        Response response = RestAssured.given()
                .contentType(APPLICATION_JSON)
                .body(uniqueUser)
                .when()
                .post(baseURI);

        String responseID = response.getBody().asString().split(" ")[6];
        USER_ID = responseID;
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
}