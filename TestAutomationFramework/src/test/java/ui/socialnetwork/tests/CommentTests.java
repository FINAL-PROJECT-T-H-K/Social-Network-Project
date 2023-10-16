package ui.socialnetwork.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
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
    @Tag("FHKT-31")
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
    @Tag("FHKT-275")
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
    @Tag("FHKT-276")
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
    @Tag("FHKT-277")
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
    @Tag("FHKT-278")
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
