package test.cases.socialnetwork;

import com.telerikacademy.testframework.CustomWebDriverManager;
import com.telerikacademy.testframework.UserActions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import pages.socialNetwork.pages.socialnetwork.*;

public class BaseTestSetup {
    static UserActions actions = new UserActions();
    //PAGES
    public static HomePage homePage;
    public static PersonalProfilePage personalProfilePage;
    public static LogoutPage logoutPage;
    public static RegisterPage registerPage;
    public static LoginPage loginPageSocial;
    public static PostPage postPageSocial;
    public static CommentPage commentPage;


    @BeforeClass
    public static void setUp() {
        UserActions.loadBrowser("social.network.homepage");
        WebDriver driver = CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver();

        homePage = new HomePage(driver);
        loginPageSocial = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        postPageSocial = new PostPage(driver);
        logoutPage = new LogoutPage(driver);
        personalProfilePage = new PersonalProfilePage(driver);
        commentPage = new CommentPage(driver);

    }

    @AfterClass
    public static void tearDown() {
        UserActions.quitDriver();
    }

    public static void loginSocial() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser("user");
    }
}

