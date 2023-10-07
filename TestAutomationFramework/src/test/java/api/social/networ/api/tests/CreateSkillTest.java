package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static apiSocialNetwork.Endpoints.*;
import static apiSocialNetwork.JSONRequests.EDITED_SKILLS_BODY;
import static apiSocialNetwork.JSONRequests.SKILLS_BODY;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateSkillTest extends BaseTestSetup {

    @Test
    public static void create_a_skill() {
        baseURI = BASE_URL + CREATE_SKILL_ENDPOINT;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(SKILLS_BODY)
                .when()
                .post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));

        ///NEED TO COLLECT SKILL ID IN VARIABLE

        //ASSERT
    }


    @Test
    public void editSkill() {

        baseURI = BASE_URL + EDIT_SKILL_ENDPOINT;

        Response response = RestAssured
                .given()
                .queryParam("skill", "SecondSkillRest")
                .queryParam("skillId", 923)
                .contentType(ContentType.JSON)
                .body(EDITED_SKILLS_BODY)
                .when()
                .put(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode, "Incorrect status code");

        ///ASSERT FOR ID AND SKILL
    }

    @Test
    public static void get_all_skills() {
        baseURI = BASE_URL + SKILL_ENDPOINT;

        Response response = RestAssured.given()
                .queryParam("sorted", "true")
                .queryParam("unsorted", "false")
                .when()
                .get(baseURI);

        System.out.println(response.asString());

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));

        ///ASSERT
    }

    @Test
    public static void delete_skills() {
        baseURI = BASE_URL + DELETE_SKILL_ENDPOINT;

        Response response = RestAssured
                .given()
                .queryParam("skillId", 923)
                .when()
                .put(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode, "Incorrect status code. Expected 200 for successful deletion.");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        assertTrue(responseBody.isEmpty(), "Response body is not empty");
    }
}

