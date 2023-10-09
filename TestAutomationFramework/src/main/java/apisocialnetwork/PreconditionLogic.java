package apisocialnetwork;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static apisocialnetwork.Constants.APPLICATION_JSON;
import static apisocialnetwork.Constants.USER_ID;
import static apisocialnetwork.Endpoints.BASE_URL;
import static apisocialnetwork.Endpoints.REGISTER_ENDPOINT;
import static apisocialnetwork.JSONRequests.REGISTRATION_BODY;
import static io.restassured.RestAssured.baseURI;

public class PreconditionLogic {

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
}
