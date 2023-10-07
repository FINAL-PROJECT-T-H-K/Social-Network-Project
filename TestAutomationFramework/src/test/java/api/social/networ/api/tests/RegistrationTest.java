package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.Endpoints.*;
import static apisocialnetwork.JSONRequests.REGISTRATION_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class RegistrationTest extends BaseTestSetup {

    @Test
    public void registerUser_Successful() {

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
        userId = responseID;

        assertEquals(responseID, userId, "User Id does not match the expected ID");

        System.out.printf("Username with name '%s' was successfully created.", USERNAME );

    }
}