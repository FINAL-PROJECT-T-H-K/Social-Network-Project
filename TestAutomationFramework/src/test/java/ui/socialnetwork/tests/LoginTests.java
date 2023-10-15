package ui.socialnetwork.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class LoginTests extends BaseTestSetup {

    String username = "";
    String password = "";
    String adminUsername = "admin";
    @Test
    public void nonAdminUserAuthentication() {
        registerAndLoginUser();
        //ASSERT
        loginPage.assertAuthenticatedUser();
    }
    @Test
    public void adminUserAuthenticationTest() {
        loginAdmin();
        //ASSERT
        loginPage.assertAdminAuthenticatedUser();
    }
    ///FEW MORE TEST
}
