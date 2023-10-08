package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.Endpoints.*;
import static apisocialnetwork.JSONRequests.PROFILE_POST;
import static apisocialnetwork.JSONRequests.REGISTRATION_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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

        ///let's check this test case as we compare same things from response body only, it is always true.
        String responseID = response.getBody().asString().split(" ")[6];
        USER_ID = responseID;

        assertEquals(responseID, USER_ID, "User Id does not match the expected ID");

        System.out.printf("Username with name '%s' was successfully created.", USERNAME );


        ///add show users post request

    }

    @Test
    public void registerReceiverUser_Successful() {

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
        RECEIVER_USERNAME = responseUsername;
        assertEquals(responseUsername, USERNAME, "Username does not match the expected username");


        ///let's check this test case as we compare same things from response body only, it is always true.
        String responseID = response.getBody().asString().split(" ")[6];
        RECEIVER_USER_ID = responseID;

        assertEquals(responseID, RECEIVER_USER_ID, "User Id does not match the expected ID");

        System.out.printf("Username with name '%s' was successfully created.", USERNAME );
    }

    @Test
    public void registerSenderUser_Successful() {

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
        SENDER_USERNAME = responseUsername;
        assertEquals(responseUsername, USERNAME, "Username does not match the expected username");

        ///let's check this test case as we compare same things from response body only, it is always true.
        String responseID = response.getBody().asString().split(" ")[6];
        SENDER_USER_ID = responseID;

        assertEquals(responseID, SENDER_USER_ID, "User Id does not match the expected ID");

        System.out.printf("Username with name '%s' was successfully created.", USERNAME );
    }
}