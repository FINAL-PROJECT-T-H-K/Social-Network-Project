package ui.socialnetwork.tests;

import org.junit.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class LoginTests extends BaseTestSetup {

    String username = "";
    String password = "";
    String adminUsername = "admin";

    @Test
    public void successfullyUserAuthentication() {

        username += registerPage.generateUser();
        password += registerPage.generatePassword();
        registerPage.registerUser(username, password);
        loginPage.loginUser(username, password);

        //ASSERT
        loginPage.assertAuthenticatedUser();
    }

    @Test
    public void successfullyAdminUserAuthentication() {

        adminUsername += registerPage.generateUser();
        password += registerPage.generatePassword();
        registerPage.registerUser(username, password);
        loginPage.loginUser(username, password);

        //ASSERT
        loginPage.assertAdminAuthenticatedUser();
    }
}
