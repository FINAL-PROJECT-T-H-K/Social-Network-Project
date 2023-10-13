package ui.socialnetwork.tests;

import org.junit.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class LoginTests extends BaseTestSetup {

    @Test
    public void successfullyUserAuthentication() {

        loginPageSocial.loginUser("user");

        //ASSERT
        loginPageSocial.assertAuthenticatedUser();
    }

    @Test
    public void successfullyAdminUserAuthentication() {

        loginPageSocial.loginAdminUser("user");

        //ASSERT
        loginPageSocial.assertAdminAuthenticatedUser();
    }
}
