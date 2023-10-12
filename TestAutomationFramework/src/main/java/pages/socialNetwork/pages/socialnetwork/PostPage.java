package pages.socialNetwork.pages.socialnetwork;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

public class PostPage extends BaseSocialPage {
    public String PostDescription = "My Post: ";

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

    public void likePublicPost() {


        if (actions.isElementVisible("post.dislike.button")) {
            actions.clickElement("post.dislike.button");
        }

        actions.waitForElementVisible("post.like.button");
        actions.clickElement("post.like.button");

        actions.waitForElementVisible("post.dislike.button");
    }

    public void unlikePublicPost() {

        if (actions.isElementVisible("post.like.button")) {
            actions.clickElement("post.like.button");
        }

        actions.waitForElementVisible("post.dislike.button");
        actions.clickElement("post.dislike.button");
    }
    public void validateTopicIsUnliked() {
        actions.assertElementPresent("post.like.button");
    }
    public void deletePost(){
        actions.waitForElementVisible("delete.post.button");
        actions.clickElement("delete.post.button");

        actions.waitForElementVisible("delete.drop.down.button");
        actions.clickElement("delete.drop.down.button");

        actions.waitForElementVisible("delete.submit.button");
        actions.clickElement("delete.submit.button");

    }

    public void clickOnTheRecentPost() {
        actions.waitForElementVisible("profile.personal.page.button");
        actions.clickElement("profile.personal.page.button");

        actions.waitForElementClickable("latest.activity.button");
        actions.clickElement("latest.activity.button");

        actions.waitForElementClickable("recently.post");
        actions.clickElement("recently.post");
    }
    public void validatePostIsDeleted(){
        actions.assertElementPresent("delete.post.message");


    }
    public void validatePostIsLiked(){
        actions.assertElementPresent("post.dislike.button");
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
        PostDescription += RandomStringUtils.randomAlphabetic(15);
        return PostDescription;
    }
}
