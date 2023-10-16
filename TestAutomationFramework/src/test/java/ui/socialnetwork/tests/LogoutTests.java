package ui.socialnetwork.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class LogoutTests extends BaseTestSetup {
    String logoutPageHeader = "Login Page";

    @Test
    public void nonAdminUserLoggedOutTest() {
        registerAndLoginUser();
        logoutPage.logoutSuccessfully();

        //ASSERT
        logoutPage.assertSuccessfulLogout();
        logoutPage.validateLogoutPage(logoutPageHeader);
    }
    @Test
    public void adminUserLoggedOutTest(){
        loginAdmin();
        logoutPage.logoutSuccessfully();

        //ASSERT
        logoutPage.assertSuccessfulLogout();
        logoutPage.validateLogoutPage(logoutPageHeader);
    }
}
