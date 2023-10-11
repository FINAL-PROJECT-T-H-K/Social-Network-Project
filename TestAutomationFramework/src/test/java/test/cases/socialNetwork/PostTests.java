package test.cases.socialNetwork;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.AfterTest;

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


        postPageSocial.deletePost();
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

    @Test    ///MAYBE SHOULD BE IN @AFTERCLASS
    public void deletePostTest() {
        loginSocial();
        postPageSocial.deletePost();
        //assert
        postPageSocial.validatePostIsDeleted();


    }
}

