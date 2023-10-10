package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.Endpoints.*;
import static apisocialnetwork.ErrorMessages.*;
import static apisocialnetwork.JSONRequests.*;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateSkillTest extends BaseTestSetup {

    @Test
    public static void _01_create_a_skill() {
        baseURI = BASE_URL + CREATE_SKILL_ENDPOINT;

        String skillsUnique = format("%s%s", SKILL_DESCRIPTION, UNIQUE_NAME);

        String uniqueUser = String.format(SKILLS_BODY, skillsUnique);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(uniqueUser)
                .when()
                .post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_INCORRECT_STATUS);

        System.out.println(response.getBody().asPrettyString());
        System.out.printf("Skills with name '%s' was successfully created.%n", skillsUnique);



        //ASSERT
    }


    @Test
    public void _02_editSkill() {

        if (isNull(SKILL_ID)) {
            _01_create_a_skill();
        }

        baseURI = BASE_URL + EDIT_SKILL_ENDPOINT;
        String editSkill = String.format("%s %s", EDITED_SKILLS, UNIQUE_NAME);

        Response response = RestAssured
                .given()
                .queryParam("skill", editSkill)
                .queryParam("skillId", SKILL_ID)
                .contentType(ContentType.JSON)
                .body(EDITED_SKILLS_BODY)
                .when()
                .put(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode, ERROR_MESSAGE_INCORRECT_STATUS);

        System.out.printf("Skills with name '%s' was successfully edited.%n", editSkill);

        ///ASSERT FOR ID AND SKILL
    }

    @Test
    public static void _03_get_all_skills() {
        baseURI = BASE_URL + SKILL_ENDPOINT;

        Response response = RestAssured.given()
                .queryParam("sorted", "true")
                .queryParam("unsorted", "false")
                .when()
                .get(baseURI);

        System.out.println(response.asString());

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK,ERROR_MESSAGE_INCORRECT_STATUS);

        ///ASSERT
    }

    @Test
    public static void _04_delete_skills() {

        if (isNull(SKILL_ID)) {
            _01_create_a_skill();
        }
        baseURI = BASE_URL + DELETE_SKILL_ENDPOINT;

        Response response = RestAssured
                .given()
                .queryParam("skillId", SKILL_ID)
                .when()
                .put(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode, SC_OK,ERROR_MESSAGE_INCORRECT_STATUS);

        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        assertTrue(responseBody.isEmpty(), ERROR_MESSAGE_RESPONSE_BODY_EMPTY);
    }
}

