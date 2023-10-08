package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.Endpoints.BASE_URL;
import static apisocialnetwork.Endpoints.SEND_CONNECTION_REQUEST_ENDPOINT;
import static apisocialnetwork.JSONRequests.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class ConnectionController extends BaseTestSetup {

    @BeforeTest
    public static void setupAuthentication() {
        if (isNull(RECEIVER_USER_ID)) {
            RegistrationTest registerUser = new RegistrationTest();
            registerUser.registerReceiverUser_Successful();
        }

        if (isNull(SENDER_USER_ID)) {
            RegistrationTest registerUser = new RegistrationTest();
            registerUser.registerSenderUser_Successful();
        }

        if (isNull(COOKIE_VALUE_SENDER)) {
            AuthenticateUser authenticate = new AuthenticateUser();
            authenticate._02_authenticateAndFetchCookiesSender();
        }

        if (isNull(COOKIE_VALUE_RECEIVER)) {
            AuthenticateUser authenticate = new AuthenticateUser();
            authenticate._02_authenticateAndFetchCookiesReceiver();
        }

    }

    @Test
    public void sendConnectionRequest(){

        baseURI = BASE_URL + SEND_CONNECTION_REQUEST_ENDPOINT;

        String requestBody = String.format(SEND_CONNECTION_REQ_BODY, RECEIVER_USER_ID, RECEIVER_USERNAME);

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Accept", "*/*")
                .cookie("JSESSIONID", COOKIE_VALUE_SENDER)
                .queryParam("principal",SENDER_USERNAME)
                .body(requestBody)
                .when()
                .log()
                .all()
                .post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));

    }

}
