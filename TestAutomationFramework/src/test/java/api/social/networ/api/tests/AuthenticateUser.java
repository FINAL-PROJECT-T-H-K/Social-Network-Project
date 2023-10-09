package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static java.util.Objects.isNull;

public class AuthenticateUser extends BaseTestSetup {

    @BeforeTest
    public static void setupAuthentication() {
        if (isNull(USER_ID)) {
            RegistrationTest registerUser = new RegistrationTest();
            registerUser.registerUser_Successful();
        }
    }

    @Test
    public void _02_authenticateAndFetchCookies() {
        baseURI = BASE_URL + AUTHENTICATE_ENDPOINT;

        System.out.println("Using Username: " + USERNAME);
        System.out.println("Using Password: " + PASSWORD);  ///might be deleted

        ValidatableResponse responseBody = getApplicationAuthentication()
                .when()
                .post(baseURI)
                .then()
                .assertThat()
                .statusCode(302);


        int statusCode = responseBody.extract().statusCode();
        String CookieValue = responseBody.extract().cookies().get("JSESSIONID");

        COOKIE_VALUE = CookieValue;

        Assert.assertFalse(CookieValue.isEmpty(), "Cookie value is not present");
        System.out.println("Cookie value is: " + CookieValue);
        System.out.println("Status code is: " + statusCode);


        ///add upgrade user personal profile request


        ///add upgrade user expertise profile request
    }

}



