package test.cases.socialNetwork.socialnetwork;

import org.junit.Test;

public class CreatePost extends BaseTestSetup {

    String postDescription;
    @Test
    public void createTopicWhenClickNewTopicTest(){
        loginSocial();

        postDescription = postPageSocial.generateDescription();

        postPageSocial.createPost(postDescription);
        postPageSocial.verifyTopicCreated(postDescription);

    }

}
