package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Locale;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class AuthenticateUser extends BaseTestSetup {
    public static  String RANDOM_JOB_TITLE_FIRST;
    public static  String RANDOM_JOB_TITLE;

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

        ///add upgrade user personal profile request
    }
    @Test
    public void _03_upgradeExpertiseProfile() {

        //ADD AUTH

        baseURI = BASE_URL + "/api/users/auth/" + USER_ID + "/expertise";

        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

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
                ,USER_ID, RANDOM_JOB_TITLE_FIRST,RANDOM_JOB_TITLE);


        Response response = given()
                .contentType(ContentType.JSON)
                .header("Accept","*/*")
                .cookie("JSESSIONID", COOKIE_VALUE)
                .body(body)
                .when()
                .log()
                .all()
                .post(baseURI);

        int statusCode = response.getStatusCode();
        String skill1Content = response.getBody().jsonPath().getJsonObject("skill1");
        String skillsContent = response.getBody().jsonPath().getString("skills");
//        String userId = response.getBody().jsonPath().getString("id");

        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
//        assertEquals(skill1Content, RANDOM_JOB_TITLE_FIRST, format("Response body content does not match the expected. Expected %s", RANDOM_JOB_TITLE_FIRST));
//        assertEquals(skillsContent, RANDOM_JOB_TITLE, format("Response body content does not match the expected. Expected %s", RANDOM_JOB_TITLE));
//        assertEquals(userId, USER_ID, format("Response body content does not match the expected. Expected %s", RANDOM_JOB_TITLE));

        System.out.println(response.getBody().asPrettyString());

    }

}



