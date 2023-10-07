package test.cases.socialNetwork.socialnetwork;

import com.telerikacademy.testframework.CustomWebDriverManager;
import com.telerikacademy.testframework.UserActions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import pages.socialNetwork.pages.socialNetwork.LoginPageSocial;
import pages.socialNetwork.pages.socialNetwork.PostPageSocial;
import pages.socialNetwork.pages.socialNetwork.RegisterPageSocial;

public class BaseTestSetup {
    static UserActions actions = new UserActions();

    //PAGES
    public static RegisterPageSocial registerLageSocial;
    public static LoginPageSocial loginPageSocial;
    public static PostPageSocial postPageSocial;


    @BeforeClass
    public static void setUp() {
        UserActions.loadBrowser("socialNetwork.homePage");
        WebDriver driver = CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver();

        loginPageSocial = new LoginPageSocial(driver);
        registerLageSocial = new RegisterPageSocial(driver);
        postPageSocial = new PostPageSocial(driver);

    }

    @AfterClass
    public static void tearDown() {
        UserActions.quitDriver();
    }

    public static void loginSocial() {
        LoginPageSocial loginPage = new LoginPageSocial(actions.getDriver());
        loginPage.loginUserSocial("user");
    }
}

