package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static apiSocialNetwork.Endpoints.BASE_URL;
import static apiSocialNetwork.Endpoints.CREATE_POST_ENDPOINT;
import static apiSocialNetwork.JSONRequests.POST_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;


public class CreatePost extends BaseTestSetup {


    @Test
    public static void createPost() {
        baseURI = BASE_URL + CREATE_POST_ENDPOINT;

        Response response = getApplicationAuthentication()
                .body(POST_BODY)
                .when()
                .post(CREATE_POST_ENDPOINT);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        System.out.println("Post was created successfully!");
    }
}
