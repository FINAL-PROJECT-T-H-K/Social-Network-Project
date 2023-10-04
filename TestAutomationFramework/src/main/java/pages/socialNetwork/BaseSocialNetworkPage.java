package pages.socialNetwork;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public abstract class BaseSocialNetworkPage extends BasePage {

    public BaseSocialNetworkPage(WebDriver driver, String pageUrlKey) {
        super(driver, pageUrlKey);
    }
}
