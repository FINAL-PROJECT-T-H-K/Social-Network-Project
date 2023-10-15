package ui.socialnetwork.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;


public class PostTests extends BaseTestSetup {

    @BeforeEach
    public void setupUser() {

        loginUser();
    }

    @AfterEach
    public void logOutUser() {
        homePage.clickOnHomeButton();
        logoutPage.logoutSuccessfully();
    }

    @Test
    public void createPublicPostTest() {

        postPage.createPublicPost();

        //Assert
        postPage.verifyPostCreated();
        postPage.verifyPublicPostCreated();

    }

    @Test
    public void createPrivatePostTest() {
        postPage.createPrivatePost();

        //assert
        postPage.verifyPostCreated();
        postPage.verifyPrivatePostCreated();

    }

    @Test
    public void anonymousUserCannotSeePrivatePostsTest() {

        postPage.createPrivatePost();
        homePage.clickOnHomeButton();
        logoutPage.logoutSuccessfully();
        homePage.clickOnLatestPostsButton();

        postPage.validateAnonymousUserCannotSeePrivatePosts();
        loginUser();

    }

    ///replace like.post.button locator
    @Test
    public void likePostWhenClickLikeButtonTest() {
        homePage.clickOnLatestPostsButton();
        postPage.likePublicPost();

        //assert
        postPage.validatePostIsLiked();

    }

    @Test
    public void dislikePostWhenClickLikeButtonTest() {

        homePage.clickOnLatestPostsButton();
        postPage.dislikePublicPost();

        //assert
        postPage.validateTopicIsUnliked();

    }

    @Test
    public void editPostTest() {

        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        postPage.userEditPost();

        //assert
        postPage.validatePostIsEdited();
    }


    @Test    ///MAYBE SHOULD BE IN @AFTERCLASS
    public void deletePostTest() {

        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        postPage.deletePost();
        //assert
        postPage.validatePostIsDeleted();

    }

    //ADMIN USER TESTS//
    @Test

    public void adminUserCreatePublicPostsTest() {
        loginAdmin();

    public void createPublicPostsAdminUserTest() {

        postPage.createPublicPost();

        //Assert
        postPage.verifyPostCreated();
        postPage.verifyPublicPostCreated();

    }

    @Test
    public void adminUserCreatePrivatePostsTest() {
        postPage.createPrivatePost();

        //Assert
        postPage.verifyPostCreated();
        postPage.verifyPrivatePostCreated();

    }

    @Test
    public void adminUserLikePostWhenClickLikeButtonTest() {

        homePage.clickOnLatestPostsButton();
        postPage.likePublicPost();

        //assert
        postPage.validatePostIsLiked();
    }

    @Test
    public void adminUserDislikePostWhenClickLikeButtonTest() {

        homePage.clickOnLatestPostsButton();
        postPage.dislikePublicPost();

        //assert
        postPage.validateTopicIsUnliked();

    }

    @Test
    public void adminUserEditPostTest() {
        homePage.clickOnLatestPostsButton();
        commentPage.clickOnExploreThePost();
        postPage.userEditPost();

        //assert
        postPage.validatePostIsEdited();

    }

    }
}


