package test.cases.socialNetwork;

import org.junit.Test;

public class RegisterTests extends BaseTestSetup {
    String username;
    String password;
    String adminUsername = "admin";
    String adminPassword;

    @Test
    public void successfulRegisterUserTest() {

        registerPage.navigateToPage();

        username = registerPage.generateUser();
        password = registerPage.generatePassword();

        registerPage.registerUser(username, password);

        //ASSERT
        registerPage.assertSuccsesfullRegistration();

    }

    @Test
    public void successfulRegisterAdminUserTest() {

        registerPage.navigateToPage();

        adminUsername += registerPage.generateUser();
        adminPassword += registerPage.generatePassword();

        registerPage.registerUser(adminUsername, adminPassword);

        //ASSERT
        registerPage.assertSuccsesfullRegistration();

        System.out.println(adminUsername);
        System.out.println(adminPassword);
    }
}


