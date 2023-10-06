package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static api.base.BaseTestSetup.getApplicationAuthentication;
import static apiSocialNetwork.Constants.PASSWORD;
import static apiSocialNetwork.Constants.USERNAME;
import static apiSocialNetwork.Endpoints.BASE_URL;
import static apiSocialNetwork.Endpoints.LOGIN_ENDPOINT;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;
import static org.testng.Assert.assertEquals;

public class AuthenticateUser extends BaseTestSetup {

    @Test
    public static void authenticateAndFetchCookies() {

        baseURI = format("%s%s",BASE_URL, LOGIN_ENDPOINT);

        System.out.println("Using Username: " + USERNAME);
        System.out.println("Using Password: " + PASSWORD);

        Response response = getApplicationAuthentication()
                .log().all()
                .post(baseURI);

        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);
        assertEquals(statusCode, SC_MOVED_TEMPORARILY, format("Incorrect status code. Expected %s.", SC_MOVED_TEMPORARILY));

        //ONE MORE ASSERT

        System.out.println(response.getBody().asPrettyString());

    }
}

