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
    public void createPrivatePostTest() {
        loginUser();    ///maybe in beforeAll
        postPage.createPrivatePost();

        //assert
        postPage.verifyPostCreated();
        postPage.verifyPrivatePostCreated();


    }

    ///replace like.post.button locator
    @Test
    public void likePostWhenClickLikeButtonTest() {
        loginUser();
        homePage.clickOnLatestPostsButton();
        postPage.likePublicPost();

        //assert
        postPage.validatePostIsLiked();
    }

    @Test
    public void dislikePostWhenClickLikeButtonTest() {
        loginUser();
        homePage.clickOnLatestPostsButton();
        postPage.dislikePublicPost();

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



    //////////////////////////ADMIN TESTS
    @Test
    public void createPublicPostsAdminUserTest() {
        loginAdmin();
        postPage.createPublicPost();

        //Assert
        postPage.verifyPostCreated();
        postPage.verifyPublicPostCreated();

    }

    @Test
    public void adminUserCreatePrivatePostsTest() {
        loginAdmin();
        postPage.createPrivatePost();

        //Assert
        postPage.verifyPostCreated();
        postPage.verifyPrivatePostCreated();

    }

    @Test
    public void adminUserLikePostWhenClickLikeButtonTest() {
        loginAdmin();
        homePage.clickOnLatestPostsButton();
        postPage.likePublicPost();

        //assert
        postPage.validatePostIsLiked();
    }

    @Test
    public void adminUserDislikePostWhenClickLikeButtonTest() {
        loginAdmin();
        homePage.clickOnLatestPostsButton();
        postPage.dislikePublicPost();

        //assert
        postPage.validateTopicIsUnliked();

    }
    @Test
    public void adminUsereditPostTest() {
        loginAdmin();
        homePage.clickOnLatestPostsButton();
        commentPage.clickOnExploreThePost();
        postPage.userEditPost();

        //assert
        postPage.validatePostIsEdited();

    }
}

