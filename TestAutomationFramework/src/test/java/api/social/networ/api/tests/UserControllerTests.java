package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import groovy.util.logging.Slf4j;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.logging.Logger;

import static api.social.networ.api.tests.ConnectionController.baseTestSetup;
import static apisocialnetwork.Constants.*;
import static apisocialnetwork.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

@Slf4j
public class UserControllerTests extends BaseTestSetup {
    FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService());
    Logger logger = Logger.getLogger("");
    public static String RANDOM_JOB_TITLE_FIRST;
    public static String RANDOM_JOB_TITLE;

    @BeforeMethod //this runs before every test method
    public static void setupForTests() {

        if (isNull(USER_ID)) {
            RegistrationTest registerUser = new RegistrationTest();
            registerUser.registerUser_Successful();
        }
    }

    @Test
    public void authenticateUserAndFetchCookies() {

        baseURI = BASE_URL + AUTHENTICATE_ENDPOINT;

        ValidatableResponse responseBody = getApplicationAuthentication()
                .when()
                .post(baseURI)
                .then()
                .assertThat()
                .statusCode(302);

        String CookieValue = responseBody.extract().cookies().get("JSESSIONID");
        COOKIE_VALUE = CookieValue;

        Assert.assertFalse(CookieValue.isEmpty(), "Cookie value is not present");

        logger.info("Username " + USERNAME);
        logger.info("Password " + PASSWORD);

    }

    @Test
    public void upgradeExpertiseProfile() {

        baseTestSetup.loginUser();
        baseURI = BASE_URL + "/api/users/auth/" + USER_ID + "/expertise";

        RANDOM_JOB_TITLE_FIRST = fakeValuesService.bothify("JobTitle?????");
        RANDOM_JOB_TITLE = fakeValuesService.bothify("JobTitles?????");

        String body = String.format("{  \"availability\": 100,  " +
                        "\"category\": {" +
                        "    \"id\": 120,    " +
                        "\"name\": \"Hairdresser\"  }" +
                        ",  " +
                        "\"id\": %s,  " +
                        "\"skill1\": \"%s\",  " +
                        "\"skills\": [    " +
                        "\"%s\"  ]}"
                , USER_ID, RANDOM_JOB_TITLE_FIRST, RANDOM_JOB_TITLE);

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Accept", "*/*")
                .cookie("JSESSIONID", COOKIE_VALUE)
                .body(body)
                .when()
                .log()
                .all()
                .post(baseURI);

        int statusCode = response.getStatusCode();

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(response.asString());

        String skill1Content = String.valueOf(jsonObject.getAsJsonArray("skills").get(0).getAsJsonObject().get("skill"));

        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));

        ///check this with replace all
//        assertEquals(skill1Content, RANDOM_JOB_TITLE, format("Response body content does not match the expected. "
//                + "Expected %s", RANDOM_JOB_TITLE));

    }

    public void updatePersonalProfile() {

        baseTestSetup.loginUser();

        baseURI = BASE_URL + "/api/users/auth/" + USER_ID + "/personal";

        RANDOM_JOB_TITLE_FIRST = fakeValuesService.bothify("JobTitle?????");
        RANDOM_JOB_TITLE = fakeValuesService.bothify("JobTitles?????");

        String body = String.format("{\n" +
                "  \"birthYear\": \"string\",\n" +
                "  \"firstName\": \"string\",\n" +
                "  \"id\": 0,\n" +
                "  \"lastName\": \"string\",\n" +
                "  \"location\": {\n" +
                "    \"city\": {\n" +
                "      \"city\": \"string\",\n" +
                "      \"country\": {},\n" +
                "      \"id\": 0\n" +
                "    },\n" +
                "    \"id\": 0\n" +
                "  },\n" +
                "  \"memberSince\": \"string\",\n" +
                "  \"personalReview\": \"string\",\n" +
                "  \"picture\": \"string\",\n" +
                "  \"picturePrivacy\": true,\n" +
                "  \"sex\": \"MALE\"\n" +
                "}");


        Response response = given()
                .contentType(ContentType.JSON)
                .header("Accept", "*/*")
                .queryParam("name", USERNAME)
                .cookie("JSESSIONID", COOKIE_VALUE)
                .body(body)
                .when()
                .log()
                .all()
                .post(baseURI);

        int statusCode = response.getStatusCode();
    }

}



