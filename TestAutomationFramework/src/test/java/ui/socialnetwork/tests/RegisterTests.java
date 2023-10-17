package ui.socialnetwork.tests;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

import java.util.logging.Logger;

import static com.telerikacademy.testframework.Constants.*;

public class RegisterTests extends BaseTestSetup {


    Logger logger = Logger.getLogger("");

    @Test
    @Tag("FHKT-264")
    public void nonAdminUserRegisterTest() {
        ///username = "username" + registeredPage.generatedUser();
        username += registerPage.generateUser();
        password += registerPage.generatePassword();
        registerUser(username, password);

        //ASSERT
        registerPage.assertSuccessfulRegistration();
        logger.info(username);
        logger.info(password);

    }

    @Test
    @Tag("FHKT-265")
    public void adminUserRegisterTest() {
        adminUsername += registerPage.generateUser();
        password += registerPage.generatePassword();
        registerUser(adminUsername, password);
        //ASSERT
        registerPage.assertSuccessfulRegistration();
        logger.info(adminUsername);
        logger.info(password);
    }
}


