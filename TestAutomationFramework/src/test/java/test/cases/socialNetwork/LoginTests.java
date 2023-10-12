package test.cases.socialNetwork;

import org.junit.Test;

public class LoginTests extends BaseTestSetup {

    @Test
    public void successfullyUserAuthentication() {

        loginPageSocial.loginUserSocial("user");
        //ASSERT
        loginPageSocial.assertAuthenticatedUser();
    }
}
