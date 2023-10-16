package ui.socialnetwork.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class LoginTests extends BaseTestSetup {

    @Test
    @Tag("FHKT-15")
    public void nonAdminUserAuthentication() {
        registerAndLoginUser();          //SEPARATE METHOD
        //ASSERT
        loginPage.assertAuthenticatedUser();
    }
    @Test
    @Tag("FHKT-17")
    public void adminUserAuthenticationTest() {
        loginAdmin();
        //ASSERT
        loginPage.assertAdminAuthenticatedUser();
    }
}
