package apisocialnetwork;

public class JSONRequests {
    public static final String REGISTRATION_BODY = "{\n" +
            "    \"category\": {\n" +
            "        \"id\": 100,\n" +
            "        \"name\": \"All\"\n" +
            "    },\n" +
            "    \"confirmPassword\": \"alabala123\",\n" +
            "    \"email\": \"test@gmail.com\",\n" +
            "    \"password\": \"%s\",\n" +
            "    \"username\": \"%s\"\n" +
            "}";

    public static final String POST_BODY = "{\n" +
            " \"content\": \"MyFirstPost\",\n" +
            " \"picture\": \"No picture\",\n" +
            " \"public\": true\n" +
            "}";

    public static final String COMMENT_BODY =
            "{\n" +
                    "    \"content\": \"CommentContent\",\n" +
                    "    \"postId\": \"YourPostId\",\n" +
                    "    \"userId\": \"YourGeneratedIdUser\"\n" +
                    "}";


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

    public static final String EDITED_SKILLS_BODY ="{\"newField\": \"newValue\"}";
}
