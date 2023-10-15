package ui.socialnetwork.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class LogoutTests extends BaseTestSetup {
    @BeforeEach
    public void setupUser() {
        loginUser();
    }

    @Test
    public void nonAdminUserLoggedOutTest(){
        logoutPage.logoutSuccessfully();

        //ASSERT
        logoutPage.assertSuccessfulLogout();
        logoutPage.validateLoggedOut();
    }
}
