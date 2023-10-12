package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.Locale;
import java.util.logging.Logger;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.Endpoints.BASE_URL;
import static apisocialnetwork.ErrorMessages.ERROR_MESSAGE_INCORRECT_STATUS_LOGIN;
import static apisocialnetwork.ShowMessages.ERROR_MESSAGE_COOKIE_VALUE_IS_NOT_PRESENT;
import static apisocialnetwork.ShowMessages.SHOW_MESSAGE_LOGIN_USED_USERNAME_PASSWORD_COOKIE;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class UserControllerTest extends BaseTestSetup {
    Logger logger = Logger.getLogger("");
    public static String RANDOM_JOB_TITLE_FIRST;
    public static String RANDOM_JOB_TITLE;
    FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService());

    @Test
    public void loginAndFetchCookiesTest() {

        createAndRegisterUser();
        ValidatableResponse responseBody = loginUser();

        int statusCode = responseBody.extract().statusCode();

        assertEquals(statusCode, SC_MOVED_TEMPORARILY, ERROR_MESSAGE_INCORRECT_STATUS_LOGIN);
        Assert.assertFalse(COOKIE_VALUE.isEmpty(), ERROR_MESSAGE_COOKIE_VALUE_IS_NOT_PRESENT);

        logger.info(SHOW_MESSAGE_LOGIN_USED_USERNAME_PASSWORD_COOKIE);

    }

    @Test
    public void upgradeExpertiseProfile() {

        createAndRegisterUser();
        loginUser();
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

    }

}



