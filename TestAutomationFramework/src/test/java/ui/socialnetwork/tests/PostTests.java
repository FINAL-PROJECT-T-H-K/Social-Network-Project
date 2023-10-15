package ui.socialnetwork.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;


public class PostTests extends BaseTestSetup {


    @BeforeEach
    public void setupUser() {
        registerAndLoginUser();
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
        registerAndLoginUser();

    }
    @Test
    public void likePostWhenClickLikeButtonTest() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        homePage.clickOnLatestPostsButton();
        postPage.likePublicPost();

        //assert
        postPage.validatePostIsLiked();

    }

    @Test
    public void dislikePostWhenClickLikeButtonTest() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
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


}


