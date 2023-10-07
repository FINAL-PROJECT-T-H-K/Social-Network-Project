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
    public void createPost(String generateDescription) {

        actions.waitForElementClickable("new.post.button");
        actions.clickElement("new.post.button");

        actions.waitForElementClickable("post.description");
        actions.typeValueInField(generateDescription, "post.description");
        actions.clickElement("create.post.button");


    }


    public void verifyTopicCreated(String generateDescription) {
        actions.waitForElementClickable("explore.button");
        System.out.printf("Post with title %s is created", generateDescription);
    }

    public String generateDescription() {

        String generatedDescription = postDescription;

        randomCount = random.nextInt(20);
        while (randomCount >= 0) {
            String randomStr = RandomStringUtils.randomAlphabetic(33);
            String randomNumber = RandomStringUtils.randomNumeric(33);
            String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(33);
            generatedDescription = generatedDescription.concat(randomStr).concat(" ").concat(randomNumber).concat(" ")
                    .concat(randomAlphanumeric).concat("\n");
            randomCount--;
        }
        return generatedDescription;
    }


}
