package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.ErrorMessages.ERROR_MESSAGE_INCORRECT_STATUS_LOGIN;
import static apisocialnetwork.ShowMessages.ERROR_MESSAGE_COOKIE_VALUE_IS_NOT_PRESENT;
import static apisocialnetwork.ShowMessages.SHOW_MESSAGE_LOGIN_USED_USERNAME_PASSWORD_COOKIE;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;
import static org.testng.Assert.assertEquals;

public class AuthenticateUserTest extends BaseTestSetup {

    Logger logger = Logger.getLogger("");

    @Test
    public void LoginAndFetchCookiesTest() {

        if (isNull(USER_ID)) {
            createAndRegisterUser();
        }

        ValidatableResponse responseBody = loginUser();

        int statusCode = responseBody.extract().statusCode();

        assertEquals(statusCode, SC_MOVED_TEMPORARILY, ERROR_MESSAGE_INCORRECT_STATUS_LOGIN);
        Assert.assertFalse(COOKIE_VALUE.isEmpty(), ERROR_MESSAGE_COOKIE_VALUE_IS_NOT_PRESENT);

        System.out.println(SHOW_MESSAGE_LOGIN_USED_USERNAME_PASSWORD_COOKIE);

        logger.info ("Username " + USERNAME);
        logger.info("Password " + PASSWORD);

    }

}



