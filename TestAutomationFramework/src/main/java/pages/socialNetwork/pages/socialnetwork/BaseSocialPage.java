package pages.socialNetwork.pages.socialnetwork;

import com.telerikacademy.testframework.Utils;
import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseSocialPage extends BasePage {
    public BaseSocialPage(WebDriver driver, String urlKey) {
        super(driver, urlKey);
    }

}
