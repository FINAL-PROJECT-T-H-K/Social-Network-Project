package apisocialnetwork;

import static apisocialnetwork.Constants.*;

public class JSONRequests {
    public static final String REGISTRATION_BODY = "{\n" +
            "    \"category\": {\n" +
            "        \"id\": 100,\n" +
            "        \"name\": \"All\"\n" +
            "    },\n" +
            "    \"confirmPassword\": \"%s\",\n" +
            "    \"email\": \"%s\",\n" +
            "    \"password\": \"%s\",\n" +
            "    \"username\": \"%s\"\n" +
            "}";

    public static final String POST_BODY =  String.format("{\n" +
            " \"content\": \"%s\",\n" +
            " \"picture\": \"No picture\",\n" +
            " \"public\": true\n" +
            "}",POST_DESCRIPTION);

    public static final String COMMENT_BODY = String.format("{\n" +
            "    \"content\": \"%s\",\n" +
            "    \"postId\": \"%s\",\n" +
            "    \"userId\": \"%s\"\n" +
            "}",COMMENT_DESCRIPTION, POST_ID, USER_ID);


    public static final String PROFILE_POST = "{\n" +
            "  \"index\": 0,\n" +
            "  \"next\": true,\n" +
            "  \"searchParam1\": \"\",\n" +
            "  \"searchParam2\": \"\",\n" +
            "  \"size\": 10\n" +
            "}";

    public static final String SKILLS_BODY = "{\n" +
            "  \"category\": {\n" +
            "    \"id\": 153,\n" +
            "    \"name\": \"TESTREST\"\n" +
            "  },\n" +
            "  \"skill\": \"%s\",\n" +
            "  \"skillId\": 400\n" +
            "}";

    public static final String EDITED_SKILLS_BODY = "{\"newField\": \"newValue\"}";

    public static final String SEND_CONNECTION_REQ_BODY = "{\"id\": %s, \"username\": \"%s\"}";

    public static final String POST_EDIT = String.format("{"
            + "\"content\": \"%s\","
            + "\"picture\": \"No picture\","
            + "\"public\": true"
            + "}",EDITED_POST) ;
}
