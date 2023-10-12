package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static apisocialnetwork.Constants.*;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class ConnectionControllerTest extends BaseTestSetup {

    @Test
    public void sendConnectionRequestTest() {

        createAndRegisterUserReceiver();
        createAndRegisterUser();
        loginUser();

        Response response = sendRequest();

        int statusCode = response.getStatusCode();
        System.out.println(response.getBody().asPrettyString());

        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        assertEquals(USERNAME, response.body().asString().split(" ")[0],
                "Expected sender username is " + USERNAME);
        assertEquals(USERNAME_RECEIVER, response.body().asString().split(" ")[5],
                "Expected receiver username is " + USERNAME_RECEIVER);

    }

    @Test
    public void approveConnectionRequestTest() {

        createAndRegisterUserReceiver();
        createAndRegisterUser();
        loginUser();
        sendRequest();
        loginUserReceiver();
        showReceivedRequests();

        Response response = approveRequest();


        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        System.out.println(response.getBody().asPrettyString());

        assertEquals(USERNAME_RECEIVER, response.body().asString().split(" ")[0],
                "Expected receiver username is " + USERNAME_RECEIVER);
        assertEquals(USERNAME, response.body().asString().split(" ")[4],
                "Expected sender username is " + USERNAME);

    }

}
