package test.cases.socialNetwork.socialnetwork;

import org.junit.Test;
public class LoginTest extends BaseTestSetup {



    @Test
    public void validate_userAuthentication(){

        loginPageSocial.loginUserSocial("user");
        //ASSERT
        loginPageSocial.assertAuthenticatedUser();
    }
}
