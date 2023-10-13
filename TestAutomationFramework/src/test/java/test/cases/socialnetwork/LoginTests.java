package test.cases.socialnetwork;

import org.junit.Test;

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
