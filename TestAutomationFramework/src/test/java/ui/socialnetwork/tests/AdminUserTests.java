package ui.socialnetwork.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class AdminUserTests extends BaseTestSetup {

    @BeforeEach
    public void setupUser() {
        loginAdmin();
    }

    @AfterEach
    public void logOutUser() {
        homePage.clickOnHomeButton();
        logoutPage.logoutSuccessfully();
    }


    @Test
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
