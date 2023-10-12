package test.cases.socialNetwork;

import org.junit.Test;

public class LogoutTests extends BaseTestSetup{


    @Test
    public void validate_userLoggedSuccessful(){
        loginSocial();

        logoutPage.logoutSuccessfully();

        //ASSERT
        logoutPage.assertSuccsesfullLogout();
        logoutPage.validateLoggedOut();
    }
}
