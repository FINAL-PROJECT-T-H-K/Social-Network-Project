package pages.socialNetwork.pages.socialnetwork;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class LogoutPage extends BaseSocialPage {
    public LogoutPage(WebDriver driver) {
        super(driver, "socialNetwork.homePage");


    }
    @Test
    public void logoutSuccessfully() {
        actions.waitForElementClickable("socialNetwork.logout.button");
        actions.clickElement("socialNetwork.logout.button");

    }
    public void assertSuccsesfullLogout() {
        actions.waitForElementPresent("socialNetwork.signIn.button");;
    }
    public void validateLoggedOut() {
        actions.waitForElementClickable("socialNetwork.submitButton");
    }
}
