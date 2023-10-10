package apisocialnetwork;

import static apisocialnetwork.Constants.*;

public class ShowMessages {

    public static final String SHOW_MESSAGE_EDITED_SKILL = String.format("Skills with name '%s' was successfully edited.%n", EDITED_SKILLS + UNIQUE_NAME);
    public static final String SHOW_MESSAGE_DELETED_SKILL = String.format("Skill with name'%s' was successfully deleted",SKILL_ID);
    public static final String SHOW_MESSAGE_RESPONSE_BODY = "Show the response body: \n";
    public static final String SHOW_MESSAGE_CREATED_SKILL_SILL_ID = String.format("Skills with name '%s' and Id '%s' was successfully created.%n",SKILL_DESCRIPTION, SKILL_ID);
    public static final String CORRECT_REGISTER_RETURN_MESSAGE = String.format("User with name %s and id %s was created", USERNAME, USER_ID);
}
