package ui.socialnetwork.tests;

import org.junit.Test;
import ui.socialnetwork.base.BaseTestSetup;

import java.util.logging.Logger;

public class RegisterTests extends BaseTestSetup {

    Logger logger = Logger.getLogger("");
    String username;
    String password;
    String adminUsername = "admin";
    String adminPassword;

    @Test
    public void successfulRegisterUserTest() {
        username = registerPage.generateUser();
        password = registerPage.generatePassword();

        registerPage.registerUser(username, password);
        logger.info(username);
        logger.info(password);
        //ASSERT
        registerPage.assertSuccessfulRegistration();

    }

    @Test
    public void successfulRegisterAdminUserTest() {
        adminUsername += registerPage.generateUser();
        adminPassword += registerPage.generatePassword();

        registerPage.registerUser(adminUsername, adminPassword);

        //ASSERT
        registerPage.assertSuccessfulRegistration();

//        System.out.println(adminUsername);
//        System.out.println(adminPassword);
    }
}


