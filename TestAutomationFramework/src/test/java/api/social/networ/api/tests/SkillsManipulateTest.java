package api.social.networ.api.tests;

import api.base.BaseTestSetup;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.ErrorMessages.*;
import static apisocialnetwork.Helper.isValid;
import static apisocialnetwork.JSONRequests.SKILLS_BODY;
import static apisocialnetwork.ShowMessages.*;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SkillsManipulateTest extends BaseTestSetup {

    @Test(priority = 1)
    public static void createSkillSuccessfully() {

        Response response = createSkill();

        System.out.printf(SHOW_MESSAGE_RESPONSE_BODY + response.getBody().asPrettyString());

        int statusCode = response.getStatusCode();
        String responseBodySkill = response.getBody().jsonPath().getString("skill");
        String responseBodyCategoryName = response.getBody().jsonPath().getString("category.name");

        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_INCORRECT_STATUS);
        assertEquals(responseBodySkill, SKILL_DESCRIPTION, ERROR_MESSAGE_RESPONSE_SKILL_DESCRIPTION);
        assertEquals(responseBodyCategoryName, SKILL_NAME, ERROR_MESSAGE_RESPONSE_SKILL_NAME);
        assertTrue(isValid(SKILLS_BODY), ERROR_MESSAGE_BODY_NOT_VALID_JSON);

        System.out.printf(SHOW_MESSAGE_CREATED_SKILL_SILL_ID);

    }

    @Test(priority = 3)
    public static void getAllSkillsSuccessfully() {

        Response response = showAllSkills();

        System.out.println(SHOW_MESSAGE_RESPONSE_BODY + response.asPrettyString());

        int statusCode = response.getStatusCode();

        assertTrue(isValid(SKILLS_BODY), ERROR_MESSAGE_BODY_NOT_VALID_JSON);
        assertEquals(statusCode, SC_OK, ERROR_MESSAGE_INCORRECT_STATUS);

        System.out.println(response.getBody().asPrettyString());

    }

    @Test(priority = 4)
    public static void deleteSkillSuccessfully() {

        if (isNull(SKILL_ID)) {
            createSkill();
        }

        Response response = deleteSkill();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asPrettyString();

        assertEquals(SC_OK, statusCode, SC_OK, ERROR_MESSAGE_INCORRECT_STATUS);
        assertTrue(responseBody.isEmpty(), ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

        System.out.println(SHOW_MESSAGE_DELETED_SKILL);

    }

    @Test(priority = 2)
    public void editSkillSuccessfully() {

        if (isNull(SKILL_ID)) {
            createSkill();
        }

        Response response = editSkill();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(SC_OK, statusCode, ERROR_MESSAGE_INCORRECT_STATUS);
        assertTrue(responseBody.isEmpty(), ERROR_MESSAGE_RESPONSE_BODY_EMPTY);

        System.out.printf(SHOW_MESSAGE_EDITED_SKILL);

    }

}

