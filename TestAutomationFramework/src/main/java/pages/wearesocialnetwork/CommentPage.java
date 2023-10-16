package pages.wearesocialnetwork;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

public class CommentPage extends BaseSocialPage {

    public String commentText = "My comment is ";
    public String editCommentText = "Edit comment to: ";
    public CommentPage(WebDriver driver) {
        super(driver, "social.network.homepage");
    }

    public void createCommentUnderPost(String generateRandomComment) {
        actions.waitForElementClickable("create.comments.fields");
        actions.typeValueInField(generateRandomComment, "create.comments.fields");

        actions.waitForElementClickable("create.comments.button");
        actions.clickElement("create.comments.button");

        actions.waitForElementVisible("show.comments.button");
    }

    public void userLikeCommentUnderThePost() {
        if (actions.isElementVisible("dislike.comment.button")) {
            actions.clickElement("dislike.comment.button");
        }

        actions.waitForElementVisible("like.comment.button");
        actions.clickElement("like.comment.button");

        actions.waitForElementVisible("dislike.comment.button");
    }

    public void userDislikeCommentUnderThePost() {
        if (actions.isElementVisible("like.comment.button")) {
            actions.clickElement("like.comment.button");
        }

        actions.waitForElementVisible("dislike.comment.button");
        actions.clickElement("dislike.comment.button");

        actions.waitForElementVisible("dislike.comment.button");
    }

    public void userEditCommentUnderThePost(String generateRandomComment) {
        actions.waitForElementVisible("edit.comment.button");
        actions.clickElement("edit.comment.button");

        actions.waitForElementClickable("edit.comment.field");
        actions.typeValueInField(generateRandomComment, "edit.comment.field");

        actions.waitForElementVisible("edit.comment.submit");
        actions.clickElement("edit.comment.submit");

        actions.waitForElementVisible("show.comments.button");
    }

    public void userDeleteCommentUnderThePost() {
        actions.waitForElementVisible("delete.comment.button");
        actions.clickElement("delete.comment.button");

        actions.waitForElementVisible("delete.comment.drop.down.button");
        actions.clickElement("delete.comment.drop.down.button");

        actions.waitForElementVisible("delete.comment.submit.button");
        actions.clickElement("delete.comment.submit.button");

    }

    public void clickOnExploreThePost() {
        actions.waitForElementClickable("explore.button");
        actions.clickElement("explore.button");
    }

    public void clickOnShowCommentsUnderThePost()  {
        actions.scrollUp(-500);
        actions.waitForElementClickable("show.comments.button");
        actions.clickElement("show.comments.button");

    }


    public void validateTopicIsLiked() {
        actions.assertElementPresent("dislike.comment.button");
    }

    public void validateTopicIsUnliked() {
        actions.waitForElementVisible("like.comment.button");
        actions.assertElementPresent("like.comment.button");
    }

    public void verifyFirstCommentCreated() {
        actions.assertElementPresent("show.comments.button");
    }
    public void verifyCommentЕdited() {
        actions.assertElementPresent("show.comments.button");
    }
    public void verifyCommentDeleted() {
        actions.assertElementPresent("//h1[@class='mb-3 bread' and contains(text(), 'Comment deleted successfully')]");
    }

    public void validateCommentCreatedWithText(String comment){
        actions.assertElementVisible("comment.content", comment);
    }

    public void validateCommentAddedInTheLast1Minute(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        actions.assertElementAttribute("comment.time.created", "innerText", "1m");
    }

    public String generateRandomComment() {
        commentText += RandomStringUtils.randomAlphabetic(15);
        return commentText;
    }

    public String generateRandomEditComment() {
        editCommentText += RandomStringUtils.randomAlphabetic(15);
        return editCommentText;
    }
}
