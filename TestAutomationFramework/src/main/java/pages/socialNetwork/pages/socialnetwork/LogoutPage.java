package pages.socialNetwork.pages.socialnetwork;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class LogoutPage extends BaseSocialPage {
    public LogoutPage(WebDriver driver) {
        super(driver, "socialNetwork.homePage");


    }
    @Test
    public void logoutSuccessfully() {
        actions.waitForElementClickable("logout.button");
        actions.clickElement("logout.button");

    }
    public void assertSuccsesfullLogout() {
        actions.waitForElementPresent("home.page.sign.in.button");;
    }
    public void validateLoggedOut() {
        actions.waitForElementClickable("register.page.submitButton");
    }
}
