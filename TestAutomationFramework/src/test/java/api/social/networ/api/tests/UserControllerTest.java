package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.jetbrains.annotations.NotNull;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.Locale;
import java.util.logging.Logger;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.ErrorMessages.*;
import static apisocialnetwork.ShowMessages.ERROR_MESSAGE_COOKIE_VALUE_IS_NOT_PRESENT;
import static apisocialnetwork.ShowMessages.SHOW_MESSAGE_LOGIN_USED_USERNAME_PASSWORD_COOKIE;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class UserControllerTest extends BaseTestSetup {
    Logger logger = Logger.getLogger("");

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
    public void upgradeExpertiseProfileTest() {

        createAndRegisterUser();
        loginUser();

        Response response = upgradeExpertiseProfile();

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(response.asString());
        String skill1Content = String.valueOf(jsonObject.getAsJsonArray("skills").get(0).getAsJsonObject().get("skill"));
        skill1Content = skill1Content.replaceAll("[\"<>,]", "");

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_INCORRECT_STATUS);
        assertEquals(skill1Content, RANDOM_JOB_TITLE, ERROR_MESSAGE_JOB_TITLE);

    }

}



