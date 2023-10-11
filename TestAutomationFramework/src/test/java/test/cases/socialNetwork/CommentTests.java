package test.cases.socialNetwork;

import org.junit.Test;

public class CommentTests extends BaseTestSetup {
    public String commentText;

    @Test
    public void createCommentUnderThePostTests() {
        loginSocial();

        postPageSocial.clickOnLatestPosts();
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
}
