package pages.socialNetwork.pages.socialnetwork;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getConfigPropertyByKey;

public class LoginPage extends BaseSocialPage {

    public LoginPage(WebDriver driver) {
        super(driver, "socialNetwork.loginPage");
    }
    public void loginUserSocial(String userKey) {
        String username = getConfigPropertyByKey("socialNetwork.users." + userKey + ".username");
        String password = getConfigPropertyByKey("socialNetwork.users." + userKey + ".password");

        navigateToPage();

        actions.waitForElementVisible("loginPage.username");
        actions.typeValueInField(username, "loginPage.username");

        actions.waitForElementVisible("loginPage.password");
        actions.typeValueInField(password, "loginPage.password");

        actions.clickElement("loginSubmitButton");

        actions.waitForElementVisible("header.member.menu.button");
    }


    public void assertAuthenticatedUser() {
        actions.assertElementPresent("header.member.menu.button");
        actions.clickElement("header.member.menu.button");
    }
}
