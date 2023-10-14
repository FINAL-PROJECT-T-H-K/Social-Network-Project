package ui.socialnetwork.tests;

import org.junit.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class LogoutTests extends BaseTestSetup {


    @Test
    public void validate_userLoggedSuccessful(){
        loginUser();

        logoutPage.logoutSuccessfully();

        //ASSERT
        logoutPage.assertSuccessfulLogout();
        logoutPage.validateLoggedOut();
    }
}
