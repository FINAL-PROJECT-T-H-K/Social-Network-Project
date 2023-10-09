package test.cases.socialNetwork.socialnetwork;

import org.junit.Test;
import pages.socialNetwork.pages.socialnetwork.RegisterPageSocial;

public class RegisterTest extends BaseTestSetup {
    String username;
    String password;

    @Test
    public void validate_registerUser(){

        registerLageSocial.navigateToPage();

        username = registerLageSocial.generateUser();
        password =  registerLageSocial.generatePassword();

        RegisterPageSocial registerLageSocial = new RegisterPageSocial(actions.getDriver());
        registerLageSocial.registerUser(username,password);

        //ASSERT
        registerLageSocial.assertSuccsesfullRegistration();

    }
}


