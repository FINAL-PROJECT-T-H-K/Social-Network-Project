package pages.socialNetwork.pages.socialNetwork;

import com.telerikacademy.testframework.pages.BasePage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static java.lang.String.format;

public class RegisterPageSocial extends BasePage {
    Random random = new Random();
    int randomCount;
    public RegisterPageSocial(WebDriver driver) {
        super(driver,"socialNetwork.RegisterPage");
    }

    public void registerUser(String generateUser,String generatePassword) {

        actions.waitForElementClickable("socialNetwork.usernameField");
        actions.typeValueInField(generateUser, "socialNetwork.usernameField");

        actions.waitForElementClickable("socialNetwork.emailField");
        actions.typeValueInField("test@abv.bg", "socialNetwork.emailField");

        actions.waitForElementClickable("socialNetwork.passwordField");
        actions.typeValueInField(generatePassword, "socialNetwork.passwordField");

        actions.waitForElementClickable("socialNetwork.confirmPasswordField");
        actions.typeValueInField(generatePassword, "socialNetwork.confirmPasswordField");

        actions.waitForElementClickable("socialNetwork.submitButton");
        actions.clickElement("socialNetwork.submitButton");

    }

    //NEED EDIT
    public String generateUser() {
        int randomCount = random.nextInt(5);
        StringBuilder username = new StringBuilder();

        while (randomCount > 0) {
            String randomLetters = RandomStringUtils.randomAlphabetic(4);
            username.append(randomLetters);
            randomCount--;
        }

        return String.format("Username%s", username);
    }
    public String generatePassword() {
        randomCount = random.nextInt(5);
        String passwordGenerate = "";

        while (randomCount >= 0) {
            String randomPass = RandomStringUtils.randomAlphanumeric(1,7);
            passwordGenerate = passwordGenerate.concat(randomPass);
            randomCount--;
        }

        return format("%s", passwordGenerate.trim());
    }

   public void assertSuccsesfullRegistration(){
       actions.assertElementPresent("socialNetwork.successful.register");
       actions.clickElement("socialNetwork.successful.register");

   }
}
