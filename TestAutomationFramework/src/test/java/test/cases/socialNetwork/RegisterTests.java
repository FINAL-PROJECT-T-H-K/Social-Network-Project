package test.cases.socialNetwork;

import org.junit.Test;

public class RegisterTests extends BaseTestSetup {
    String username;
    String password;

    @Test
    public void successfulRegisterUserTest(){

        registerLageSocial.navigateToPage();

        username = registerLageSocial.generateUser();
        password =  registerLageSocial.generatePassword();

        registerLageSocial.registerUser(username,password);

        //ASSERT
        registerLageSocial.assertSuccsesfullRegistration();

    }
}


