package test.cases.socialNetwork;

import org.junit.Test;


public class PostTests extends BaseTestSetup {
    String postDescription;

    @Test
    public void createPublicPostTest() {
        loginSocial(); ///maybe in beforeAll

        postDescription = postPageSocial.generateDescription();
        postPageSocial.createPublicPost(postDescription);

        //Assert
        postPageSocial.verifyPostCreated(postDescription);
        postPageSocial.verifyPublicPostCreated();

    }

    @Test
    public void createPrivatePostTest() {
        loginSocial();    ///maybe in beforeAll

        postDescription = postPageSocial.generateDescription();
        postPageSocial.createPrivatePost(postDescription);

        //assert
        postPageSocial.verifyPostCreated(postDescription);
        postPageSocial.verifyPrivatePostCreated();


    }

    @Test
    public void likePostWhenClickLikeButtonTest() {
        loginSocial();

        postPageSocial.clickOnLatestPosts();
        postPageSocial.likePublicPost();

        //assert
        postPageSocial.validatePostIsLiked();
    }

    @Test
    public void unlikePostWhenClickLikeButtonTest() {
        loginSocial();
        postPageSocial.clickOnLatestPosts();

        postPageSocial.unlikePublicPost();

        //assert
        postPageSocial.validateTopicIsUnliked();


    }
    @Test ///IN PROGRESS
    public void editPostTest() {

    }


    @Test    ///MAYBE SHOULD BE IN @AFTERCLASS
    public void deletePostTest() {
        loginSocial();

        postPageSocial.clickOnTheRecentPost();
        postPageSocial.deletePost();
        //assert
        postPageSocial.validatePostIsDeleted();


    }
}

