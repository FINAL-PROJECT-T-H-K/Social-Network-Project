package ui.socialnetwork.tests;

import org.junit.Test;
import ui.socialnetwork.base.BaseTestSetup;


public class PostTests extends BaseTestSetup {


    @Test
    public void createPublicPostTest() {
        loginUser(); ///maybe in beforeAll

        postPage.createPublicPost();

        //Assert
        postPage.verifyPostCreated();
        postPage.verifyPublicPostCreated();

    }

    @Test
    public void createPublicPostsAdminUserTest() {
        loginAdmin();

        postPage.createPublicPost();

        //Assert
        postPage.verifyPostCreated();
        postPage.verifyPublicPostCreated();

    }

    @Test
    public void createPrivatePostTest() {
        loginUser();    ///maybe in beforeAll

        postPage.createPrivatePost();

        //assert
        postPage.verifyPostCreated();
        postPage.verifyPrivatePostCreated();


    }

    @Test
    public void likePostWhenClickLikeButtonTest() {
        loginUser();

        homePage.clickOnLatestPostsButton();
        postPage.likePublicPost();

        //assert
        postPage.validatePostIsLiked();
    }

    @Test
    public void unlikePostWhenClickLikeButtonTest() {
        loginUser();
        homePage.clickOnLatestPostsButton();

        postPage.unlikePublicPost();

        //assert
        postPage.validateTopicIsUnliked();


    }

    @Test
    public void editPostTest() {
        loginUser();

        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        postPage.userEditPost();

        //assert
        postPage.validatePostIsEdited();

    }


    @Test    ///MAYBE SHOULD BE IN @AFTERCLASS
    public void deletePostTest() {
        loginUser();

        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        postPage.deletePost();
        //assert
        postPage.validatePostIsDeleted();


    }
}

