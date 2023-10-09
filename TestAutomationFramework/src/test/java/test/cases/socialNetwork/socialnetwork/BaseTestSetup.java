package test.cases.socialNetwork.socialnetwork;

import com.telerikacademy.testframework.CustomWebDriverManager;
import com.telerikacademy.testframework.UserActions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import pages.socialNetwork.pages.socialnetwork.LoginPage;
import pages.socialNetwork.pages.socialnetwork.PostPage;
import pages.socialNetwork.pages.socialnetwork.RegisterPageSocial;

public class BaseTestSetup {
    static UserActions actions = new UserActions();

    //PAGES
    public static RegisterPageSocial registerLageSocial;
    public static LoginPage loginPageSocial;
    public static PostPage postPageSocial;


    @BeforeClass
    public static void setUp() {
        UserActions.loadBrowser("socialNetwork.homePage");
        WebDriver driver = CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver();

        loginPageSocial = new LoginPage(driver);
        registerLageSocial = new RegisterPageSocial(driver);
        postPageSocial = new PostPage(driver);

    }

    @AfterClass
    public static void tearDown() {
        UserActions.quitDriver();
    }

    public static void loginSocial() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUserSocial("user");
    }
}

