package ui.socialnetwork.tests;

import org.junit.Test;
import ui.socialnetwork.base.BaseTestSetup;


public class PostTests extends BaseTestSetup {
    String postDescription;

    @Test
    public void createPublicPostTest() {
        loginSocial(); ///maybe in beforeAll

        postDescription = postPage.generateDescription();
        postPage.createPublicPost(postDescription);

        //Assert
        postPage.verifyPostCreated(postDescription);
        postPage.verifyPublicPostCreated();

    }
    @Test
    public void createPublicPostsAdminUserTest() {
        loginPage.loginAdminUser("user");

        postDescription = postPage.generateDescription();
        postPage.createPublicPost(postDescription);

        //Assert
        postPage.verifyPostCreated(postDescription);
        postPage.verifyPublicPostCreated();

    }

    @Test
    public void createPrivatePostTest() {
        loginSocial();    ///maybe in beforeAll

        postDescription = postPage.generateDescription();
        postPage.createPrivatePost(postDescription);

        //assert
        postPage.verifyPostCreated(postDescription);
        postPage.verifyPrivatePostCreated();


    }

    @Test
    public void likePostWhenClickLikeButtonTest() {
        loginSocial();

        homePage.clickOnLatestPostsButton();
        postPage.likePublicPost();

        //assert
        postPage.validatePostIsLiked();
    }

    @Test
    public void unlikePostWhenClickLikeButtonTest() {
        loginSocial();
        homePage.clickOnLatestPostsButton();

        postPage.unlikePublicPost();

        //assert
        postPage.validateTopicIsUnliked();


    }
    @Test ///IN PROGRESS
    public void editPostTest() {

    }


    @Test    ///MAYBE SHOULD BE IN @AFTERCLASS
    public void deletePostTest() {
        loginSocial();

        postPage.clickOnTheRecentPost();
        postPage.deletePost();
        //assert
        postPage.validatePostIsDeleted();


    }
}

