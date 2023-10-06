package apiSocialNetwork;

public class Endpoints {

    public static final String BASE_URL = "http://localhost:8081";
    public static final String REGISTER_ENDPOINT = "/api/users/";
    public static final String GET_ALL_POSTS_ENDPOINT = "/api/post/";
    public static final String GET_ALL_PROFILE_POSTS = "/api/users/949/posts";
    public static final String LOGIN_ENDPOINT = "/authenticate";
    public static final String CREATE_SKILL_ENDPOINT= "/api/skill/create";
    public static final String EDIT_SKILL_ENDPOINT="/api/skill/edit?skill=";
    public static final String SKILL_ENDPOINT = "/api/skill";
    public static final String DELETE_SKILL_ENDPOINT = "/api/skill/delete?skillId=";
    public static final String COMMENT_ENDPOINT = "/api/comment/auth/creator";
    public static final String CREATE_POST_ENDPOINT = "/api/post/auth/creator";
    public static final String EDIT_POST_ENDPOINT = "/editor";
    public static final String LIKE_POST_ENDPOINT = "/likesUp";
    public static final String LOGOUT_ENDPOINT = "/logout";
    public static final String AUTHENTICATE_ENDPOINT = "/authenticate";


}
