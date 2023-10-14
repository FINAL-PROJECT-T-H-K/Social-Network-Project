package ui.socialnetwork.tests;

import org.junit.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class LoginTests extends BaseTestSetup {

    String username = "";
    String password = "";
    String adminUsername = "admin";

    @Test
    public void successfullyUserAuthentication() {

        loginUser();

        //ASSERT
        loginPage.assertAuthenticatedUser();
    }

    @Test
    public void successfullyAdminUserAuthentication() {

      loginAdmin();

        //ASSERT
        loginPage.assertAdminAuthenticatedUser();
    }


    ///FEW MORE TEST
}
