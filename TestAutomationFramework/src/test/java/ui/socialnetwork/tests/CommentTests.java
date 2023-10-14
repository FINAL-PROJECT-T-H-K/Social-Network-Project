package ui.socialnetwork.tests;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import ui.socialnetwork.base.BaseTestSetup;

public class CommentTests extends BaseTestSetup {

    public String commentText;
    public String editedComment;

    @Test
    public void createCommentUnderThePostTests() {
        loginUser();

        homePage.clickOnLatestPostsButton();
        commentPage.clickOnExploreThePost();
        commentText = commentPage.generateRandomComment();
        commentPage.createCommentUnderPost(commentText);

        //assert
        commentPage.verifyCommentCreated();

    }

    @Test
    public void likeCommentUnderThePostTests() {
        loginUser();
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
    public void editCommentUnderThePostTests() {
        loginUser();
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

    @Test  //maybe in @AfterEach
    public void deleteCommentUnderThePostTests() {
        loginUser();
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

    ///////////////////////////////ADMIN USER IN PROGRESS
    @Test
    public void adminUserCreateCommentUnderThePostTests() {
        loginAdmin();

        homePage.clickOnLatestPostsButton();
        commentPage.clickOnExploreThePost();
        commentText = commentPage.generateRandomComment();
        commentPage.createCommentUnderPost(commentText);

        //assert
        commentPage.verifyCommentCreated();

    }
    //   @AfterEach
    //   public void tearDownTest(){
    //       commentPage.userDeleteCommentUnderThePost();
    //   }
}
