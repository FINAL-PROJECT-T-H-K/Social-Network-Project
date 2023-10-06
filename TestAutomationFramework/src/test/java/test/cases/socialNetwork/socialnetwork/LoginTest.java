package test.cases.socialNetwork.socialnetwork;

import org.junit.Test;
public class LoginTest extends BaseTest{


    @Test
    public void validate_userAuthentication(){

        loginPageSocial.loginUserSocial("user");
        //ASSERT
        loginPageSocial.assertAuthenticatedUser();
    }
}
