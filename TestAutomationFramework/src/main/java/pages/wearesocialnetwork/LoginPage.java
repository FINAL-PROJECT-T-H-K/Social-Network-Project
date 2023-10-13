package pages.wearesocialnetwork;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getConfigPropertyByKey;

public class LoginPage extends BaseSocialPage {

    public LoginPage(WebDriver driver) {
        super(driver, "social.network.login.page");
    }

    public void loginUser(String userKey) {
        String username = getConfigPropertyByKey("social.network.users." + userKey + ".username");
        String password = getConfigPropertyByKey("social.network.users." + userKey + ".password");

        navigateToPage();

        actions.waitForElementVisible("login.page.username");
        actions.typeValueInField(username, "login.page.username");

        actions.waitForElementVisible("login.page.password");
        actions.typeValueInField(password, "login.page.password");

        actions.clickElement("login.submit.button");

        actions.waitForElementVisible("header.member.menu.button");
    }

    public void loginAdminUser(String userKey) {
        String usernameAdmin = getConfigPropertyByKey("social.network.admin." + userKey + ".username");
        String passwordAdmin = getConfigPropertyByKey("social.network.admin." + userKey + ".password");

        navigateToPage();

        actions.waitForElementVisible("login.page.username");
        actions.typeValueInField(usernameAdmin, "login.page.username");

        actions.waitForElementVisible("login.page.password");
        actions.typeValueInField(passwordAdmin, "login.page.password");

        actions.clickElement("login.submit.button");

        actions.waitForElementVisible("header.member.menu.button");
    }


    public void assertAdminAuthenticatedUser() {
        actions.assertElementPresent("header.admin.member.button");
        actions.clickElement("header.admin.member.button");
    }

    public void assertAuthenticatedUser() {
        actions.assertElementPresent("header.member.menu.button");
        actions.clickElement("header.member.menu.button");
    }
}
