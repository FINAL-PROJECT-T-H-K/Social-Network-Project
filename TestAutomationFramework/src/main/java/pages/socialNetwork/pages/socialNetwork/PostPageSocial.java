package pages.socialNetwork.pages.socialNetwork;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class PostPageSocial extends BaseSocialNetwork{

    private String postDescription = "";
    Random random = new Random();
    int randomCount;
    public PostPageSocial(WebDriver driver) {
        super(driver, "socialNetwork.homePage");
    }
    public void create_public_post(String generateDescription) {

        actions.waitForElementClickable("new.post.button");
        actions.clickElement("new.post.button");

        actions.waitForElementClickable("post.description");
        actions.typeValueInField(generateDescription, "post.description");

        actions.waitForElementClickable("choose.public.post");
        actions.clickElement("choose.public.post");

        actions.waitForElementClickable("create.post.button");
        actions.clickElement("create.post.button");


    }
    public void create_private_post(String generateDescription) {

        actions.waitForElementClickable("new.post.button");
        actions.clickElement("new.post.button");

        actions.waitForElementClickable("post.description");
        actions.typeValueInField(generateDescription, "post.description");

        actions.waitForElementClickable("choose.private.post");
        actions.clickElement("choose.private.post");

        actions.waitForElementClickable("create.post.button");
        actions.clickElement("create.post.button");


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

        String generatedDescription = postDescription;

        randomCount = random.nextInt(14);
        while (randomCount >= 0) {
            String randomStr = RandomStringUtils.randomAlphabetic(8);
            String randomNumber = RandomStringUtils.randomNumeric(8);
            String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(8);
            generatedDescription = generatedDescription.concat(randomStr).concat(" ").concat(randomNumber).concat(" ")
                    .concat(randomAlphanumeric).concat("\n");
            randomCount--;
        }
        return generatedDescription;
    }


}
