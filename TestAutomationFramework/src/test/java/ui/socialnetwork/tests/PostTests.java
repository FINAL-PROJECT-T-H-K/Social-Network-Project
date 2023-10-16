package ui.socialnetwork.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class PostTests extends BaseTestSetup {

    @BeforeEach
    public void setupUser() {
        registerAndLoginUser();
    }

    @Test
    @Tag("FHKT-26")
    public void createPublicPostTest() {
        postPage.createPublicPost();
        //Assert
        postPage.verifyPostCreated();
        postPage.verifyPublicPostCreated();

    }

    @Test
    @Tag("FHKT-109")
    public void createPrivatePostTest() {
        postPage.createPrivatePost();

        //assert
        postPage.verifyPostCreated();
        postPage.verifyPrivatePostCreated();

    }

    @Test
    @Tag("FHKT-142")
    public void anonymousUserCannotSeePrivatePostsTest() {

        postPage.createPrivatePost();
        homePage.clickOnHomeButton();
        logoutPage.logoutSuccessfully();
        homePage.clickOnLatestPostsButton();

        postPage.validateAnonymousUserCannotSeePrivatePosts();
        registerAndLoginUser();

    }

    @Test
    @Tag("FHKT-35")
    public void likePostWhenClickLikeButtonTest() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        homePage.clickOnLatestPostsButton();
        postPage.likePublicPost();

        //assert
        postPage.validatePostIsLiked();

    }

    @Test
    @Tag("FHKT-113")
    public void dislikePostWhenClickLikeButtonTest() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        homePage.clickOnLatestPostsButton();
        postPage.dislikePublicPost();
        //assert
        postPage.validateTopicIsUnliked();

    }

    @Test
    @Tag("FHKT-253")
    public void editPostTest() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        postPage.userEditPost();

        //assert
        postPage.validatePostIsEdited();
    }


    @Test
    @Tag("FHKT-283")///MAYBE SHOULD BE IN @AFTERCLASS
    public void deletePostTest() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        postPage.deletePost();
        //assert
        postPage.validatePostIsDeleted();

    }
}


