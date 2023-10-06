package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;

import static apiSocialNetwork.Constants.*;
import static apiSocialNetwork.Endpoints.*;
import static apiSocialNetwork.JSONRequests.*;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class RegistrationTest extends BaseTestSetup {

    @Test
    public void _01_registerUser_Successful() {

        baseURI = BASE_URL + REGISTER_ENDPOINT;

        String uniqueUser = String.format(REGISTRATION_BODY,PASSWORD,USERNAME);

        Response response = RestAssured.given()
                .contentType(APPLICATION_JSON)
                .body(uniqueUser)
                .when()
                .post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));

        String responseUsername = response.getBody().asString().split(" ")[3];
        assertEquals(responseUsername, USERNAME, "Username does not match the expected username");

        String responseID = response.getBody().asString().split(" ")[6];
        ID = responseID;

        assertEquals(responseID, ID, "User Id does not match the expected ID");

        System.out.printf("Username with name '%s' was successfully created.", USERNAME );

    }
}