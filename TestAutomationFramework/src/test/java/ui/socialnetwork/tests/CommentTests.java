package ui.socialnetwork.tests;

import com.telerikacademy.testframework.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.wearesocialnetwork.PostPage;
import ui.socialnetwork.base.BaseTestSetup;

import static com.telerikacademy.testframework.Constants.*;
import static com.telerikacademy.testframework.Constants.password;

public class CommentTests extends BaseTestSetup {

    @BeforeEach
    public void setupUser() {
        postDescription = "My Post:" + PostPage.generateDescription();
        commentText = commentPage.generateRandomComment();
        editedComment = commentPage.generateRandomEditComment();
        username += registerPage.generateUser();
        password += registerPage.generatePassword();
        registerUser(username, password);
        loginUser(username, password);
    }

    @Test
    @Tag("FHKT-31")
    public void createCommentUnderThePostTests() {
        postPage.createPublicPost(postDescription);
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();

        commentPage.verifyFirstCommentCreated();
        commentPage.validateCommentCreatedWithText(commentText);
        commentPage.validateCommentAddedInTheLast1Minute();
    }

    @Test
    @Tag("FHKT-255")
    public void likeCommentUnderThePostTests() {
        postPage.createPublicPost(postDescription);
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();

        int oldLikeCount = commentPage.getLikeCount("//span[@class='position']");
        commentPage.userLikeCommentUnderThePost();

        //assert
        commentPage.validateCommentIsLiked();
        commentPage.verifyLikeAmountIncreasedByOne("//span[@class='position']",oldLikeCount);
    }

    @Test
    @Tag("FHKT-113")
    public void dislikeCommentUnderThePostTests() {
        postPage.createPublicPost(postDescription);
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();
        commentPage.userDislikeCommentUnderThePost();
        //assert
        commentPage.validateCommentIsUnliked();
        //like amount decreased by 1
    }

    @Test
    @Tag("FHKT-257")
    public void editCommentUnderThePostTests() {
        postPage.createPublicPost(postDescription);
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();
        commentPage.userEditCommentUnderThePost(editedComment);
        commentPage.clickOnShowCommentsUnderThePost();
        //assert
        commentPage.validateCommentEditedWithText(editedComment);
        commentPage.validateCommentAddedInTheLast1Minute();

    }

    @Test
    @Tag("FHKT-254")
    public void deleteCommentUnderThePostTests() {
        postPage.createPublicPost(postDescription);
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();
        commentPage.userDeleteCommentUnderThePost();
        //assert
        commentPage.verifyCommentDeleted();
    }

}
