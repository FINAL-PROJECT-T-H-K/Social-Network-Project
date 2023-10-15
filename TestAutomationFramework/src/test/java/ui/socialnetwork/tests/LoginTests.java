package ui.socialnetwork.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class LoginTests extends BaseTestSetup {

    String username = "";
    String password = "";
    String adminUsername = "admin";
    @BeforeEach
    public void setupUser() {
        loginUser();
    }
    @Test
    public void nonAdminUserAuthentication() {
        //ASSERT
        loginPage.assertAuthenticatedUser();
    }
    ///FEW MORE TEST
}
