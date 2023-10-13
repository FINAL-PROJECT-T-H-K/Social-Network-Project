package ui.socialnetwork.tests;

import org.junit.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class LogoutTests extends BaseTestSetup {


    @Test
    public void validate_userLoggedSuccessful(){
        loginSocial();

        logoutPage.logoutSuccessfully();

        //ASSERT
        logoutPage.assertSuccsesfullLogout();
        logoutPage.validateLoggedOut();
    }
}
