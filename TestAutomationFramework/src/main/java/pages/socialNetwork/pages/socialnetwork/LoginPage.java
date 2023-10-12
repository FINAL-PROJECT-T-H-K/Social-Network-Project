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

        actions.waitForElementVisible("login.page.username");
        actions.typeValueInField(username, "login.page.username");

        actions.waitForElementVisible("login.page.password");
        actions.typeValueInField(password, "login.page.password");

        actions.clickElement("login.submit.button");

        actions.waitForElementVisible("header.member.menu.button");
    }


    public void assertAuthenticatedUser() {
        actions.assertElementPresent("header.member.menu.button");
        actions.clickElement("header.member.menu.button");
    }
}
