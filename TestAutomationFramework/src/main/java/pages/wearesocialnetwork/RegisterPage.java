package pages.wearesocialnetwork;

import com.telerikacademy.pages.BasePage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static java.lang.String.format;

public class RegisterPage extends BasePage {
    Random random = new Random();

    public RegisterPage(WebDriver driver) {
        super(driver, "social.network.register.page");
    }

    public void registerUser(String generateUser, String generatePassword) {
        navigateToPage();

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
    public String generateUser() {
        int randomCount = random.nextInt(3) + 1;
        StringBuilder username = new StringBuilder();

        while (randomCount > 0) {
            String randomLetters = RandomStringUtils.randomAlphabetic(6);
            username.append(randomLetters);
            randomCount--;
        }

        return "User" + username;
    }

    public String generatePassword() {
        int randomCount = random.nextInt(5) + 1;
        String passwordGenerate = "";

        while (randomCount >= 0) {
            String randomPass = RandomStringUtils.randomAlphanumeric(2, 7);
            passwordGenerate = passwordGenerate.concat(randomPass);
            randomCount--;
        }

        return "password" + passwordGenerate.trim();
    }

    public void assertSuccessfulRegistration() {
        actions.assertElementPresent("register.page.successful.register");
        actions.clickElement("register.page.successful.register");

    }
}
