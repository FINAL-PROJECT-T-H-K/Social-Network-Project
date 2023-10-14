package ui.socialnetwork.tests;

import org.junit.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class CommentTests extends BaseTestSetup {

    public String commentText;
    public String editedComment;

    @Test
    public void createCommentUnderThePostTests() {
        loginUser();

        homePage.clickOnLatestPostsButton();
        commentText = commentPage.generateRandomComment();
        commentPage.clickOnExploreThePost();

        commentPage.createCommentUnderPost(commentText);

        //assert
        commentPage.verifyCommentCreated();

    }

    @Test
    public void likeCommentUnderThePostTests() {
        loginUser();

        postPage.clickOnTheRecentPost();
        postPage.createPublicPost();
        commentPage.clickOnShowCommentsUnderThePost();
        commentPage.userLikeCommentUnderThePost();

        //assert
        commentPage.validateTopicIsLiked();
    }

    @Test
    public void dislikeCommentUnderThePostTests() {
        loginUser();

        postPage.clickOnTheRecentPost();
        commentPage.clickOnShowCommentsUnderThePost();
        commentPage.userDislikeCommentUnderThePost();

        //assert
        commentPage.validateTopicIsUnliked();
    }

    @Test
    public void editCommentUnderThePostTests() {
        loginUser();

        postPage.clickOnTheRecentPost();
        commentPage.clickOnShowCommentsUnderThePost();

        editedComment = commentPage.generateRandomEditComment();
        commentPage.userEditCommentUnderThePost(editedComment);

        //assert

    }

    @Test  //maybe in @AfterClassMethod
    public void deleteCommentUnderThePostTests() {
     //   loginSocial();

        postPage.clickOnTheRecentPost();
        commentPage.clickOnShowCommentsUnderThePost();
        commentPage.userDeleteCommentUnderThePost();

      //assert
    }
}
