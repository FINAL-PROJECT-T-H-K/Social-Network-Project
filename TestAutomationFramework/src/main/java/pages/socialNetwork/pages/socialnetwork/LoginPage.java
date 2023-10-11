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
        assertPageNavigated();

        actions.waitForElementVisible("socialNetwork.loginPage.username");
        actions.typeValueInField(username, "socialNetwork.loginPage.username");

        actions.waitForElementVisible("socialNetwork.loginPage.password");
        actions.typeValueInField(password, "socialNetwork.loginPage.password");

        actions.clickElement("socialNetwork.loginSubmitButton");

        actions.waitForElementVisible("socialNetwork.header.member.menuButton");
    }


    public void assertAuthenticatedUser() {
        actions.assertElementPresent("socialNetwork.header.member.menuButton");
        actions.clickElement("socialNetwork.header.member.menuButton");
    }
}
