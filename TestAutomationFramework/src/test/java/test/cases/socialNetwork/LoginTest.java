package test.cases.socialNetwork;

import org.junit.Test;
public class LoginTest extends BaseTestSetup {



    @Test
    public void validate_userAuthentication(){

        loginPageSocial.loginUserSocial("user");
        //ASSERT

        loginPageSocial.assertAuthenticatedUser();
    }
}
