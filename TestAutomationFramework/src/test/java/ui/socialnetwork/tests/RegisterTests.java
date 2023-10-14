package ui.socialnetwork.tests;

import org.junit.Test;
import ui.socialnetwork.base.BaseTestSetup;

import java.util.logging.Logger;

public class RegisterTests extends BaseTestSetup {

    Logger logger = Logger.getLogger("");
    String username;
    String password;
    String adminUsername = "admin";

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
        password += registerPage.generatePassword();

        registerPage.registerUser(adminUsername, password);

        //ASSERT
        registerPage.assertSuccessfulRegistration();

<<<<<<< Updated upstream
//        System.out.println(adminUsername);
//        System.out.println(adminPassword);
=======
        System.out.println(adminUsername);
        System.out.println(password);
>>>>>>> Stashed changes
    }
}


