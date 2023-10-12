package pages.socialNetwork.pages.socialnetwork;

import com.telerikacademy.testframework.pages.BasePage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static java.lang.String.format;

public class RegisterPage extends BasePage {
    Random random = new Random();
    int randomCount;
    public RegisterPage(WebDriver driver) {
        super(driver,"socialNetwork.RegisterPage");
    }

    public void registerUser(String generateUser,String generatePassword) {

        actions.waitForElementClickable("register.page.usernameField");
        actions.typeValueInField(generateUser, "register.page.usernameField");

        actions.waitForElementClickable("register.page.emailField");
        actions.typeValueInField("test@abv.bg", "register.page.emailField");

        actions.waitForElementClickable("register.page.passwordField");
        actions.typeValueInField(generatePassword, "register.page.passwordField");

        actions.waitForElementClickable("register.page.confirmPasswordField");
        actions.typeValueInField(generatePassword, "register.page.confirmPasswordField");

        actions.waitForElementClickable("register.page.submitButton");
        actions.clickElement("register.page.submitButton");

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
       actions.assertElementPresent("register.page.successful.register");
       actions.clickElement("register.page.successful.register");

   }
}
