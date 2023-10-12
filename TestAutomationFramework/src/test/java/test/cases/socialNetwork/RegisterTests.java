package test.cases.socialNetwork;

import org.junit.Test;

public class RegisterTests extends BaseTestSetup {
    String username;
    String password;

    String adminUsername = "admin";
    String adminPassword;

    @Test
    public void successfulRegisterUserTest() {

        registerLageSocial.navigateToPage();

        username = registerLageSocial.generateUser();
        password = registerLageSocial.generatePassword();

        registerLageSocial.registerUser(username, password);

        //ASSERT
        registerLageSocial.assertSuccsesfullRegistration();

    }

    @Test
    public void successfulRegisterAdminUserTest() {

        registerLageSocial.navigateToPage();

        adminUsername += registerLageSocial.generateUser();
        adminPassword += registerLageSocial.generatePassword();

        registerLageSocial.registerUser(adminUsername, adminPassword);

        //ASSERT
        registerLageSocial.assertSuccsesfullRegistration();

        System.out.println(adminUsername);
        System.out.println(adminPassword);
    }
}


