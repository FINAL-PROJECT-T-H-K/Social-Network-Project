package test.cases.socialNetwork;

import org.junit.Test;
import pages.socialNetwork.pages.socialnetwork.RegisterPage;

public class RegisterTest extends BaseTestSetup {
    String username;
    String password;

    @Test
    public void validate_registerUser(){

        registerLageSocial.navigateToPage();

        username = registerLageSocial.generateUser();
        password =  registerLageSocial.generatePassword();

        registerLageSocial.registerUser(username,password);

        //ASSERT
        registerLageSocial.assertSuccsesfullRegistration();

    }
}


