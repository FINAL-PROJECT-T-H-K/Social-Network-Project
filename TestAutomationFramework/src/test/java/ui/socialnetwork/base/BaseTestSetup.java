package ui.socialnetwork.base;

import com.telerikacademy.testframework.CustomWebDriverManager;
import com.telerikacademy.testframework.UserActions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import pages.wearesocialnetwork.*;

public class BaseTestSetup {
    static UserActions actions = new UserActions();
    //PAGES
    public static HomePage homePage;
    public static PersonalProfilePage personalProfilePage;
    public static LogoutPage logoutPage;
    public static RegisterPage registerPage;
    public static LoginPage loginPage;
    public static PostPage postPage;
    public static CommentPage commentPage;


    @BeforeClass
    public static void setUp() {
        UserActions.loadBrowser("social.network.homepage");
        WebDriver driver = CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver();

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        postPage = new PostPage(driver);
        logoutPage = new LogoutPage(driver);
        personalProfilePage = new PersonalProfilePage(driver);
        commentPage = new CommentPage(driver);

    }

//    @AfterClass
//    public static void tearDown() {
//        UserActions.quitDriver();
//    }

    //@BeforeAll
    public static void loginUser() {
        String username = registerPage.generateUser();
        String password = registerPage.generatePassword();
        registerPage.registerUser(username, password);
        loginPage.loginUser(username, password);
    }

    public static void loginAdmin() {
        String username = "admin" + registerPage.generateUser();
        String password = registerPage.generatePassword();
        registerPage.registerUser(username, password);
        loginPage.loginUser(username, password);
    }
}

