package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static apisocialnetwork.Constants.EDITED_SKILLS;
import static apisocialnetwork.Constants.SKILL_DESCRIPTION;
import static apisocialnetwork.Endpoints.*;
import static apisocialnetwork.JSONRequests.*;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateSkillTest extends BaseTestSetup {

    @Test
    public static void create_a_skill() {
        baseURI = BASE_URL + CREATE_SKILL_ENDPOINT;

        String skillsUnique = format("%s%s", SKILL_DESCRIPTION, UNIQUE_NAME);
        String uniqueUser = String.format(SKILLS_BODY,skillsUnique);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(uniqueUser)
                .when()
                .post(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));

        System.out.printf("Skills with name '%s' was successfully created.%n",skillsUnique);
        ///NEED TO COLLECT SKILL ID IN VARIABLE

        //ASSERT
    }


    @Test
    public void editSkill() {

        baseURI = BASE_URL + EDIT_SKILL_ENDPOINT;
        String editSkill = String.format("%s %s",EDITED_SKILLS, UNIQUE_NAME);

        Response response = RestAssured
                .given()
                .queryParam("skill", editSkill)
                .queryParam("skillId", 800)
                .contentType(ContentType.JSON)
                .body(EDITED_SKILLS_BODY)
                .when()
                .put(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode, "Incorrect status code");

        System.out.printf("Skills with name '%s' was successfully edited.%n",editSkill);
        ///NEED TO COLLECT SKILL ID IN VARIABLE
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
                .queryParam("skillId", 90)
                .when()
                .put(baseURI);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode, "Incorrect status code. Expected 200 for successful deletion.");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        assertTrue(responseBody.isEmpty(), "Response body is not empty");
    }
}

