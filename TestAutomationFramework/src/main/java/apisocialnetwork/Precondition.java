package apisocialnetwork;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.Constants.POST_ID;
import static apisocialnetwork.Endpoints.*;
import static apisocialnetwork.JSONRequests.POST_BODY;
import static apisocialnetwork.JSONRequests.REGISTRATION_BODY;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Precondition {

        public void registerUser(String username, String password) {

            baseURI = BASE_URL + REGISTER_ENDPOINT;

            String uniqueEmailReceiver = Utils.generateRandomEmail();

            String uniqueUser = String.format(REGISTRATION_BODY, password, uniqueEmailReceiver, password,username);

            Response response = RestAssured.given()
                    .contentType(APPLICATION_JSON)
                    .body(uniqueUser)
                    .when()
                    .post(baseURI);

            String responseID = response.getBody().asString().split(" ")[6];
            USER_ID = responseID;
        }

    public void createPost() {
        baseURI = BASE_URL + CREATE_POST_ENDPOINT;

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Accept", "*/*")
                .cookie("JSESSIONID", COOKIE_VALUE)
                .body(POST_BODY)
                .when()
                .log()
                .all()
                .post(baseURI);

        POST_ID = response.jsonPath().getString("postId");
    }
}
