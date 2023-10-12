package api.base;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.config.EncoderConfig;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.BeforeSuite;

import static apisocialnetwork.Constants.*;

import java.util.Locale;

import static apisocialnetwork.Constants.PASSWORD_RECEIVER;
import static apisocialnetwork.Endpoints.*;
import static apisocialnetwork.JSONRequests.*;
import static apisocialnetwork.Utils.fakeValueGenerator;
import static apisocialnetwork.Utils.generateRandomConstants;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BaseTestSetup {
    public static final String REQUEST = "/request/";
    /**
     * Provided configuration resolve REST Assured issue with a POST request without request body.
     * Missing configuration leads to response status code 415 (Unsupported Media Type)
     */
//    Just Testing Jenkins
    @BeforeSuite
    public void setup() {
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);

        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        generateRandomConstants("Username??????????", "Password??????????",
                "UniqueName???????????",
                "SkillDescription??????????", "??????##@example.com");
    }

    public void showReceivedRequests() {
        baseURI = BASE_URL + CONNECTION_REQUEST_ENDPOINT + USER_ID_RECEIVER + REQUEST;

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Accept", "*/*")
                .cookie("JSESSIONID", COOKIE_VALUE_RECEIVER)
                .when()
                .log()
                .all()
                .get();

        int id = response.jsonPath().getInt("[0].id");
        CONNECTION_ID = String.valueOf(id);
    }


    public static @NotNull Response createSkill() {
        baseURI = BASE_URL + CREATE_SKILL_ENDPOINT;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(SKILLS_BODY)
                .log()
                .all()
                .when()
                .post(baseURI);

        SKILL_ID = response.getBody().jsonPath().getString("skillId");
        return response;
    }
    protected static Response editSkill() {
        baseURI = BASE_URL + EDIT_SKILL_ENDPOINT;

        return RestAssured
                .given()
                .queryParam("skill", EDITED_SKILLS+UNIQUE_NAME)
                .queryParam("skillId", SKILL_ID)
                .contentType(ContentType.JSON)
                .body(EDITED_SKILLS_BODY)
                .log()
                .all()
                .when()
                .put(baseURI);
    }
    protected static Response deleteSkill() {
        baseURI = BASE_URL + DELETE_SKILL_ENDPOINT;

        return RestAssured
                .given()
                .queryParam("skillId", SKILL_ID)
                .when()
                .put(baseURI);
    }
    protected static Response showAllSkills() {
        baseURI = BASE_URL + SKILL_ENDPOINT;

        return RestAssured.given()
                .queryParam("sorted", "true")
                .queryParam("unsorted", "false")
                .log()
                .all()
                .when()
                .get(baseURI);
    }
    protected static @NotNull Response createAndRegisterUser() {
        baseURI = BASE_URL + REGISTER_ENDPOINT;

        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        USERNAME = fakeValuesService.bothify("Username??????????");
        PASSWORD = fakeValuesService.bothify("Password??????????");

        String registrationBody = String.format(REGISTRATION_BODY, PASSWORD, RANDOM_EMAIL, PASSWORD, USERNAME);

        Response response = given()
                .contentType(APPLICATION_JSON)
                .body(registrationBody)
                .log()
                .all()
                .when()
                .post(baseURI);

        USER_ID = response.getBody().asString().split(" ")[6];

        return response;
    }
    protected static @NotNull Response createAndRegisterUserReceiver() {
        baseURI = BASE_URL + REGISTER_ENDPOINT;

        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        USERNAME_RECEIVER = fakeValuesService.bothify("UsernameReceiver??????????");
        PASSWORD_RECEIVER = fakeValuesService.bothify("PasswordReceiver??????????");

        String registrationBody = String.format(REGISTRATION_BODY, PASSWORD_RECEIVER, RANDOM_EMAIL, PASSWORD_RECEIVER, USERNAME_RECEIVER);

        Response response = given()
                .contentType(APPLICATION_JSON)
                .body(registrationBody)
                .log()
                .all()
                .when()
                .post(baseURI);

        USER_ID_RECEIVER = response.getBody().asString().split(" ")[6];

        return response;
    }
    protected static @NotNull ValidatableResponse loginUser() {
        baseURI = BASE_URL + AUTHENTICATE_ENDPOINT;

        PreemptiveBasicAuthScheme preemptiveBasicAuthScheme = new PreemptiveBasicAuthScheme();
        preemptiveBasicAuthScheme.setUserName(USERNAME);
        preemptiveBasicAuthScheme.setPassword(PASSWORD);
        RestAssured.authentication = preemptiveBasicAuthScheme;

        ValidatableResponse responseBody = RestAssured
                .given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .queryParam("username", USERNAME)
                .queryParam("password", PASSWORD)
                .when()
                .post(baseURI)
                .then()
                .assertThat()
                .statusCode(302);

        COOKIE_VALUE = responseBody.extract().cookies().get("JSESSIONID");

        return responseBody;
    }

    protected static @NotNull Response upgradeExpertiseProfile() {

        createAndRegisterUser();
        loginUser();

        baseURI = BASE_URL + API_USERS_AUTH + USER_ID + "/expertise";

        fakeValueGenerator("JobTitle?????", "JobTitles?????");

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Accept", "*/*")
                .cookie("JSESSIONID", COOKIE_VALUE)
                .body(EXPERTISE_BODY)
                .when()
                .log()
                .all()
                .post(baseURI);

        return response;
    }
    protected static @NotNull ValidatableResponse loginUserReceiver() {
        baseURI = BASE_URL + AUTHENTICATE_ENDPOINT;

        PreemptiveBasicAuthScheme preemptiveBasicAuthScheme = new PreemptiveBasicAuthScheme();
        preemptiveBasicAuthScheme.setUserName(USERNAME_RECEIVER);
        preemptiveBasicAuthScheme.setPassword(PASSWORD_RECEIVER);
        RestAssured.authentication = preemptiveBasicAuthScheme;

        ValidatableResponse responseBody = RestAssured
                .given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .queryParam("username", USERNAME_RECEIVER)
                .queryParam("password", PASSWORD_RECEIVER)
                .when()
                .post(baseURI)
                .then()
                .assertThat()
                .statusCode(302);

        COOKIE_VALUE_RECEIVER = responseBody.extract().cookies().get("JSESSIONID");

        return responseBody;
    }
    protected static Response showAllPosts() {
        baseURI = BASE_URL + GET_ALL_POSTS_ENDPOINT;

        return given()
                .queryParam("sorted", "true")
                .queryParam("unsorted", "false")
                .when()
                .get(baseURI);
    }
    protected static Response showAllProfilePosts() {
        baseURI = GET_PROFILE_POSTS;

        return given()
                .contentType(ContentType.JSON)
                .cookies("JSESSIONID", COOKIE_VALUE)
                .body(PROFILE_POST)
                .when()
                .get(baseURI);
    }
    protected static @NotNull Response createPost() {
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

        return response;
    }
    protected static Response editProfilePost() {
        baseURI = BASE_URL + EDIT_POST;

        return RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .queryParam("postId", POST_ID)
                .body(POST_EDIT)
                .when()
                .put(baseURI);
    }
    protected static Response likePost() {
        baseURI = BASE_URL + LIKE_POST;

        return RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .contentType(ContentType.JSON)
                .when()
                .post(baseURI);
    }
    protected static Response deletePost() {
        baseURI = BASE_URL + DELETE_POSTS;

        return given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .queryParam("postId", POST_ID)
                .when()
                .delete(baseURI);
    }
    protected static @NotNull Response createComment() {
        baseURI = BASE_URL + COMMENT_ENDPOINT;

        String commentBody = String.format(COMMENT_BODY,COMMENT_DESCRIPTION,POST_ID,USER_ID);

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Accept","*/*")
                .cookies("JSESSIONID", COOKIE_VALUE)
                .body(commentBody)
                .when()
                .post(baseURI);

        COMMENT_ID = response.jsonPath().getString("commentId");

        return response;
    }
    protected static Response showComment() {
        baseURI = SHOW_CREATED_COMMENTS;
        Response response = RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .when()
                .get(baseURI);
        return response;
    }
    protected static Response sendRequest() {
        baseURI = BASE_URL + SEND_CONNECTION_REQUEST_ENDPOINT;

        String requestBody = String.format(SEND_CONNECTION_REQ_BODY, USER_ID_RECEIVER, USERNAME_RECEIVER);

        return given()
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
    protected static Response approveRequest() {
        baseURI = BASE_URL + CONNECTION_REQUEST_ENDPOINT + USER_ID_RECEIVER + CONNECTION_REQUEST_APPROVE_ENDPOINT;

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Accept", "*/*")
                .cookie("JSESSIONID", COOKIE_VALUE_RECEIVER)
                .queryParam("requestId", CONNECTION_ID)
                .when()
                .log()
                .all()
                .post(baseURI);

        return response;

    }
    protected static Response editComment() {
        baseURI = BASE_URL + EDITED_COMMENT;

        Response response = RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .contentType(ContentType.JSON)
                .when()
                .put(baseURI);
        return response;
    }
    protected static Response deleteComment() {
        baseURI = BASE_URL + DELETE_COMMENT;
        Response response = RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .queryParam("commentId", COMMENT_ID)
                .contentType(ContentType.JSON)
                .when()
                .delete(baseURI);
        return response;
    }
    protected static Response likeComment() {
        baseURI = BASE_URL + LIKED_COMMENT;

        Response response = RestAssured
                .given()
                .cookies("JSESSIONID", COOKIE_VALUE)
                .contentType(ContentType.JSON)
                .when()
                .post(baseURI);
        return response;
    }
}