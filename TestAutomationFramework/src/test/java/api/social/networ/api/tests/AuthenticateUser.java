package api.social.networ.api.tests;

import api.base.BaseTestSetup;

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
            registerUser.registerUserTest();
        }
    }

    @Test
    public void _02_authenticateAndFetchCookies() {
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

        int statusCode = responseBody.extract().statusCode();
        Assert.assertFalse(CookieValue.isEmpty(), "Cookie value is not present");
        System.out.println("Cookie value is: " + CookieValue);
        System.out.println("Status code is: " + statusCode);

    }
}



