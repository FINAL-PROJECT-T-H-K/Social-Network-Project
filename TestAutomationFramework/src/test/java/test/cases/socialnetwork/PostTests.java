package test.cases.socialnetwork;

import org.junit.Test;


public class PostTests extends BaseTestSetup {
    String postDescription;

    @Test
    public void createPublicPostTest() {
        loginSocial(); ///maybe in beforeAll

        postDescription = postPageSocial.generateDescription();
        postPageSocial.createPublicPost(postDescription);

        //Assert
        postPageSocial.verifyPostCreated(postDescription);
        postPageSocial.verifyPublicPostCreated();

    }
    @Test
    public void createPublicPostsAdminUserTest() {
        loginPageSocial.loginAdminUser("user");

        postDescription = postPageSocial.generateDescription();
        postPageSocial.createPublicPost(postDescription);

        //Assert
        postPageSocial.verifyPostCreated(postDescription);
        postPageSocial.verifyPublicPostCreated();

    }

    @Test
    public void createPrivatePostTest() {
        loginSocial();    ///maybe in beforeAll

        postDescription = postPageSocial.generateDescription();
        postPageSocial.createPrivatePost(postDescription);

        //assert
        postPageSocial.verifyPostCreated(postDescription);
        postPageSocial.verifyPrivatePostCreated();


    }

    @Test
    public void likePostWhenClickLikeButtonTest() {
        loginSocial();

        homePage.clickOnLatestPostsButton();
        postPageSocial.likePublicPost();

        //assert
        postPageSocial.validatePostIsLiked();
    }

    @Test
    public void unlikePostWhenClickLikeButtonTest() {
        loginSocial();
        homePage.clickOnLatestPostsButton();

        postPageSocial.unlikePublicPost();

        //assert
        postPageSocial.validateTopicIsUnliked();


    }
    @Test ///IN PROGRESS
    public void editPostTest() {

    }


    @Test    ///MAYBE SHOULD BE IN @AFTERCLASS
    public void deletePostTest() {
        loginSocial();

        postPageSocial.clickOnTheRecentPost();
        postPageSocial.deletePost();
        //assert
        postPageSocial.validatePostIsDeleted();


    }
}

