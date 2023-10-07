package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static apiSocialNetwork.Endpoints.*;
import static apiSocialNetwork.JSONRequests.PROFILE_POST;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class ShowProfilePosts extends BaseTestSetup {

    @Test
    public void showAllProfilePosts_Successful() {
        baseURI = BASE_URL + GET_ALL_PROFILE_POSTS;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(PROFILE_POST)
                .get(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));


    }
}
