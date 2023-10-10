package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import apisocialnetwork.ShowMessages;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.events.Event;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.Endpoints.*;
import static apisocialnetwork.ErrorMessages.*;
import static apisocialnetwork.Helper.isValid;
import static apisocialnetwork.JSONRequests.REGISTRATION_BODY;
import static apisocialnetwork.ShowMessages.CORRECT_REGISTER_RETURN_MESSAGE;
import static apisocialnetwork.ShowMessages.SHOW_MESSAGE_RESPONSE_BODY;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RegistrationTest extends BaseTestSetup {

    @Test
    public void registerUserTest() {

        Response response = createAndRegisterUser();

        int statusCode = response.getStatusCode();
        String responseReturnMessage =response.asPrettyString();
        String responseUsername = response.getBody().asString().split(" ")[3];
        String responseID = response.getBody().asString().split(" ")[6];

        System.out.println(SHOW_MESSAGE_RESPONSE_BODY +responseReturnMessage);

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_INCORRECT_STATUS);
        assertEquals(responseReturnMessage,CORRECT_REGISTER_RETURN_MESSAGE,ERROR_MESSAGE_INCORRECT_STATUS);
        assertEquals(responseID, USER_ID, ERROR_MESSAGE_USER_ID);
        assertEquals(responseUsername,USERNAME,ERROR_MESSAGE_USERNAME);

    }

}