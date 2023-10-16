package ui.socialnetwork.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

import java.time.LocalDateTime;

import static com.telerikacademy.testframework.Constants.commentText;
import static com.telerikacademy.testframework.Constants.editedComment;
import static com.telerikacademy.testframework.Utils.formatDateTime;
import static com.telerikacademy.testframework.Utils.getCurrentDateTime;

public class CommentTests extends BaseTestSetup {

    @BeforeEach
    public void setupLogin() {
        registerAndLoginUser();
    }

    @Test
    public void createCommentUnderThePostTests() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentText = commentPage.generateRandomComment();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();

        commentPage.verifyFirstCommentCreated();
        commentPage.validateCommentCreatedWithText(commentText);
        commentPage.validateCommentAddedInTheLast1Minute();
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
    public void editCommentUnderThePostTests() {
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

    //   @AfterEach
    //   public void tearDownTest(){
    //       commentPage.userDeleteCommentUnderThePost();
    //   }
}
