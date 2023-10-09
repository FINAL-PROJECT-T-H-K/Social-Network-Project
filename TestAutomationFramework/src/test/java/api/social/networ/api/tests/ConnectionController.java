package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import apisocialnetwork.PreconditionLogic;
import apisocialnetwork.Utils;
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
    static PreconditionLogic preconditionLogic = new PreconditionLogic();

    @BeforeTest
    public static void setupAuthentication() {

        preconditionLogic.registerUser(USERNAME, PASSWORD);

        if (isNull(COOKIE_VALUE)) {
            AuthenticateUser authenticate = new AuthenticateUser();
            authenticate._02_authenticateAndFetchCookies();
        }
    }

    @Test
    public void sendConnectionRequest() {

        String usernameReceiver = Utils.generateUniqueUsername();
        String password = Utils.generateUniquePassword();
        preconditionLogic.registerUser(usernameReceiver, password);

        baseURI = BASE_URL + SEND_CONNECTION_REQUEST_ENDPOINT;

        String requestBody = String.format(SEND_CONNECTION_REQ_BODY, USER_ID, usernameReceiver);

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Accept", "*/*")
                .cookie("JSESSIONID", COOKIE_VALUE)
                .queryParam("principal", USERNAME)
                .body(requestBody)
                .when()
                .log()
                .all()
                .post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));

    }

    @Test
    public void approveConnectionRequest (){

        ///preconditions
        ///take below preconditions from PreconditionLogic class
        //1.logout sender user
        //2.Login receiver user

        ///implement approveConnectionReq
        //logic for getting connectionId


    }

}
