package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static apisocialnetwork.Constants.COOKIE_VALUE;
import static apisocialnetwork.Constants.USER_ID;
import static apisocialnetwork.ErrorMessages.ERROR_MESSAGE_INCORRECT_STATUS_LOGIN;
import static apisocialnetwork.ShowMessages.ERROR_MESSAGE_COOKIE_VALUE_IS_NOT_PRESENT;
import static apisocialnetwork.ShowMessages.SHOW_MESSAGE_LOGIN_USED_USERNAME_PASSWORD_COOKIE;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;
import static org.testng.Assert.assertEquals;

public class AuthenticateUserTest extends BaseTestSetup {

    @Test
    public void LoginAndFetchCookiesTest() {

        if (isNull(USER_ID)) {
            createAndRegisterUser();
        }

        ValidatableResponse responseBody = LoginUser();

        int statusCode = responseBody.extract().statusCode();

        System.out.println(SHOW_MESSAGE_LOGIN_USED_USERNAME_PASSWORD_COOKIE);

        assertEquals(statusCode, SC_MOVED_TEMPORARILY, ERROR_MESSAGE_INCORRECT_STATUS_LOGIN);
        Assert.assertFalse(COOKIE_VALUE.isEmpty(), ERROR_MESSAGE_COOKIE_VALUE_IS_NOT_PRESENT);

    }

}



