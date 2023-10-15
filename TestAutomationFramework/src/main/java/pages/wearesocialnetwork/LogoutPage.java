package pages.wearesocialnetwork;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class LogoutPage extends BaseSocialPage {
    public LogoutPage(WebDriver driver) {
        super(driver, "social.network.homepage");


    }
    @Test
    public void logoutSuccessfully() {
        actions.waitForElementClickable("logout.button");
        actions.clickElement("logout.button");

    }
    public void assertSuccessfulLogout() {
        actions.waitForElementPresent("home.page.sign.in.button");;
        ///use same locator key naming format pattern
    }
    public void validateLoggedOut() {
        actions.waitForElementClickable("login.submit.button");
    }
}
