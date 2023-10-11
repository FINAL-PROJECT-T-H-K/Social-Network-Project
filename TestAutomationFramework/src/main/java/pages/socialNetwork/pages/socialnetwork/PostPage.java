package pages.socialNetwork.pages.socialnetwork;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

public class PostPage extends BaseSocialPage {

    public String commentText = "My comment is ";

    public PostPage(WebDriver driver) {
        super(driver, "socialNetwork.homePage");
    }

    public void createPublicPost(String generateDescription) {

        actions.waitForElementClickable("new.post.button");
        actions.clickElement("new.post.button");

        actions.waitForElementClickable("post.description");
        actions.typeValueInField(generateDescription, "post.description");

        actions.waitForElementClickable("choose.public.post");
        actions.clickElement("choose.public.post");

        actions.waitForElementClickable("create.post.button");
        actions.clickElement("create.post.button");


    }

    public void createPrivatePost(String generateDescription) {

        actions.waitForElementClickable("new.post.button");
        actions.clickElement("new.post.button");

        actions.waitForElementClickable("post.description");
        actions.typeValueInField(generateDescription, "post.description");

        actions.waitForElementClickable("choose.private.post");
        actions.clickElement("choose.private.post");

        actions.waitForElementClickable("create.post.button");
        actions.clickElement("create.post.button");


    }

    public void clickOnLatestPosts() {
        actions.waitForElementClickable("latest.post.button");
        actions.clickElement("latest.post.button");
    }

    public void likePublicPost() {


        if (actions.isElementVisible("dislike.button")) {
            actions.clickElement("dislike.button");
        }

        actions.waitForElementVisible("like.button");
        actions.clickElement("like.button");

        actions.waitForElementVisible("dislike.button");
    }

    public void unlikePublicPost() {

        if (actions.isElementVisible("like.button")) {
            actions.clickElement("like.button");
        }

        actions.waitForElementVisible("dislike.button");
        actions.clickElement("dislike.button");
    }
    public void validateTopicIsUnliked(){
        actions.assertElementPresent("like.button");
    }
    public void deletePost(){


    }
    public void validatePostIsLiked(){
        actions.assertElementPresent("dislike.button");
    }

    public void verifyPostCreated(String generateDescription) {
        actions.waitForElementClickable("explore.button");
        System.out.printf("Post with title %s is created", generateDescription);
    }

    public void verifyPublicPostCreated() {
        actions.assertElementPresent("public.post.displayed");
    }

    public void verifyPrivatePostCreated() {
        actions.assertElementPresent("private.post.displayed");

    }

    public String generateDescription() {
        commentText += RandomStringUtils.randomAlphabetic(15);
        return commentText;
    }
}
