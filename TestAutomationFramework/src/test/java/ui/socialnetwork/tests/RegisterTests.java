package ui.socialnetwork.tests;


import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

import java.util.logging.Logger;

public class RegisterTests extends BaseTestSetup {

    Logger logger = Logger.getLogger("");
    String username;
    String password;
    String adminUsername = "admin";

   @Test
    public void nonAdminUserRegisterTest() {
        username = registerPage.generateUser();
        password = registerPage.generatePassword();
        registerPage.registerUser(username, password);
        //ASSERT
        registerPage.assertSuccessfulRegistration();
        logger.info(username);
        logger.info(password);

    }

    @Test
    public void adminUserRegisterTest() {
        adminUsername += registerPage.generateUser();
        password += registerPage.generatePassword();
        registerPage.registerUser(adminUsername, password);
        //ASSERT
        registerPage.assertSuccessfulRegistration();
        logger.info(adminUsername);
        logger.info(password);
    }
}


