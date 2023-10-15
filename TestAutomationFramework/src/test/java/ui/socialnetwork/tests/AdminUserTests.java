package ui.socialnetwork.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class AdminUserTests extends BaseTestSetup {
    public String commentText;
    public String editedComment;

    @BeforeEach
    public void setupUser() {
        loginAdmin();
    }
    @Test
    public void adminUserViewAllUsersTest() {
        homePage.clickOnGoToAdminZoneButton();
        homePage.clickOnViewAllUsersButton();

        homePage.verifyAdminViewAllUsers();

    }

    @Test
    public void adminUserCreatePublicPostsTest() {
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
/////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void adminUserCreateCommentUnderThePostTests() {
        homePage.clickOnLatestPostsButton();
        commentPage.clickOnExploreThePost();
        commentText = commentPage.generateRandomComment();
        commentPage.createCommentUnderPost(commentText);
        commentPage.verifyCommentCreated();
    }
    @Test
    public void likeCommentUnderThePostTests() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentText = commentPage.generateRandomComment();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();
        commentPage.userLikeCommentUnderThePost();
        //assert
        commentPage.validateTopicIsLiked();
    }
    @Test
    public void dislikeCommentUnderThePostTests() {
        loginUser();
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentText = commentPage.generateRandomComment();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();
        commentPage.userDislikeCommentUnderThePost();
        //assert
        commentPage.validateTopicIsUnliked();
    }
    @Test
    public void adminUserEditCommentUnderThePostTests() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentText = commentPage.generateRandomComment();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();
        editedComment = commentPage.generateRandomEditComment();
        commentPage.userEditCommentUnderThePost(editedComment);
        //assert
        commentPage.verifyCommentЕdited();
    }
    @Test
    public void deleteCommentUnderThePostTests() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentText = commentPage.generateRandomComment();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();
        commentPage.userDeleteCommentUnderThePost();
        //assert
        commentPage.verifyCommentDeleted();
    }
}
