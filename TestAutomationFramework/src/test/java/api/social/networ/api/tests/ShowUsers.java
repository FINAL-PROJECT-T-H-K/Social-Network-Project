package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static apiSocialNetwork.Endpoints.BASE_URL;
import static apiSocialNetwork.Endpoints.GET_ALL_PROFILE_POSTS;
import static apiSocialNetwork.JSONRequests.PROFILE_POST;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class ShowUsers extends BaseTestSetup {

    @Test
    public void showAllProfilePosts_Successful() {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(PROFILE_POST)
                .get(GET_ALL_PROFILE_POSTS);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));


    }
}
