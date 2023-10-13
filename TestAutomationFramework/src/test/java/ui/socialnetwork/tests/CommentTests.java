package ui.socialnetwork.tests;

import org.junit.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class CommentTests extends BaseTestSetup {
    public String commentText;
    public String editedComment;

    @Test
    public void createCommentUnderThePostTests() {
        loginSocial();

        homePage.clickOnLatestPostsButton();
        commentText = commentPage.generateRandomComment();
        commentPage.clickOnExploreThePost();

        commentPage.createCommentUnderPost(commentText);

        //assert
        commentPage.verifyCommentCreated();

    }

    @Test
    public void likeCommentUnderThePostTests() {
        loginSocial();

        postPageSocial.clickOnTheRecentPost();
        commentPage.clickOnShowCommentsUnderThePost();
        commentPage.userLikeCommentUnderThePost();

        //assert
        commentPage.validateTopicIsLiked();
    }

    @Test
    public void dislikeCommentUnderThePostTests() {
        loginSocial();

        postPageSocial.clickOnTheRecentPost();
        commentPage.clickOnShowCommentsUnderThePost();
        commentPage.userDislikeCommentUnderThePost();

        //assert
        commentPage.validateTopicIsUnliked();
    }

    @Test
    public void editCommentUnderThePostTests() {
        loginSocial();

        postPageSocial.clickOnTheRecentPost();
        commentPage.clickOnShowCommentsUnderThePost();

        editedComment = commentPage.generateRandomEditComment();
        commentPage.userEditCommentUnderThePost(editedComment);

        //assert

    }

    @Test  //maybe in @AfterClassMethod
    public void deleteCommentUnderThePostTests() {
        loginSocial();

        postPageSocial.clickOnTheRecentPost();
        commentPage.clickOnShowCommentsUnderThePost();
        commentPage.userDeleteCommentUnderThePost();

      //assert
    }
}
