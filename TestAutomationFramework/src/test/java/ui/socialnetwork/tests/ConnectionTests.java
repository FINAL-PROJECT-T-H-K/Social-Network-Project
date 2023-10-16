package ui.socialnetwork.tests;

import api.socialnetwork.tests.ConnectionControllerTest;
import apisocialnetwork.Utils;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

import java.util.logging.Logger;

public class ConnectionTests extends BaseTestSetup {
    private static String USERNAME_SENDER;
    private static String PASSWORD_SENDER;
    private static String PASSWORD_RECEIVER;
    private static String USERNAME_RECEIVER;

    Logger logger = Logger.getLogger("");

    @Test
    public void sendingConnectionRequestToAnotherUserTest() {
        USERNAME_RECEIVER = Utils.generateUniqueUsername();
        PASSWORD_RECEIVER = Utils.generateUniquePassword();
        registerAndLoginUserWithParams(USERNAME_RECEIVER, PASSWORD_RECEIVER);

        personalProfilePage.enterPersonalProfile();
        personalProfilePage.setFirstLastNamesAndBirthdate(USERNAME_RECEIVER, USERNAME_RECEIVER);
        personalProfilePage.clickOnUpdateProfileButton();
        personalProfilePage.backToProfileInfo();
        logoutPage.logoutSuccessfully();

        USERNAME_SENDER = Utils.generateUniqueUsername();
        PASSWORD_SENDER = Utils.generateUniquePassword();
        registerAndLoginUserWithParams(USERNAME_SENDER, PASSWORD_SENDER);
        homePage.searchUserByKnownUsername(USERNAME_RECEIVER);
        homePage.clickOnUserAfterSearch();
        homePage.sendConnectionToSearchedUser();

        homePage.validateSearchedUsernameInSearchResults(USERNAME_RECEIVER, USERNAME_RECEIVER);
        homePage.verifySuccessfulConnectionRequestMessage();

        logger.info(String.format("Username with %s, and password with %s, sent connection request.",
                USERNAME_SENDER, PASSWORD_SENDER));
        logger.info(String.format("Username with %s, and password with %s, received connection request.",
                USERNAME_RECEIVER, PASSWORD_RECEIVER));
    }

    @Test
    public void approveAlreadySentConnectionRequestTest() {

        USERNAME_RECEIVER = Utils.generateUniqueUsername();
        PASSWORD_RECEIVER = Utils.generateUniquePassword();
        registerAndLoginUserWithParams(USERNAME_RECEIVER, PASSWORD_RECEIVER);

        personalProfilePage.enterPersonalProfile();
        personalProfilePage.setFirstLastNamesAndBirthdate(USERNAME_RECEIVER, USERNAME_RECEIVER);
        personalProfilePage.clickOnUpdateProfileButton();
        personalProfilePage.backToProfileInfo();
        logoutPage.logoutSuccessfully();

        USERNAME_SENDER = Utils.generateUniqueUsername();
        PASSWORD_SENDER = Utils.generateUniquePassword();
        registerAndLoginUserWithParams(USERNAME_SENDER, PASSWORD_SENDER);
        homePage.searchUserByKnownUsername(USERNAME_RECEIVER);
        homePage.clickOnUserAfterSearch();
        homePage.sendConnectionToSearchedUser();

        logoutPage.logoutSuccessfully();
        loginPage.loginUser(USERNAME_RECEIVER, PASSWORD_RECEIVER);
        homePage.clickOnPersonalProfile();
        personalProfilePage.approveReceivedConnectionRequest();

        personalProfilePage.validateReceivedConnectionRequestApproved();
        //assert friendlist increased by 1

        logger.info(String.format("Username with %s, and password with %s, sent connection request.",
                USERNAME_SENDER, PASSWORD_SENDER));
        logger.info(String.format("User with username %s, and password %s, approved connection request of user %s",
                USERNAME_RECEIVER, PASSWORD_RECEIVER, USERNAME_SENDER));

    }

    @Test
    public void disconnectFromAlreadyConnectedUserTest() {

        USERNAME_RECEIVER = Utils.generateUniqueUsername();
        PASSWORD_RECEIVER = Utils.generateUniquePassword();
        registerAndLoginUserWithParams(USERNAME_RECEIVER, PASSWORD_RECEIVER);

        personalProfilePage.enterPersonalProfile();
        personalProfilePage.setFirstLastNamesAndBirthdate(USERNAME_RECEIVER, USERNAME_RECEIVER);
        personalProfilePage.clickOnUpdateProfileButton();
        personalProfilePage.backToProfileInfo();
        logoutPage.logoutSuccessfully();

        USERNAME_SENDER = Utils.generateUniqueUsername();
        PASSWORD_SENDER = Utils.generateUniquePassword();
        registerAndLoginUserWithParams(USERNAME_SENDER, PASSWORD_SENDER);
        homePage.searchUserByKnownUsername(USERNAME_RECEIVER);
        homePage.clickOnUserAfterSearch();
        homePage.sendConnectionToSearchedUser();

        logoutPage.logoutSuccessfully();
        loginPage.loginUser(USERNAME_RECEIVER, PASSWORD_RECEIVER);
        homePage.clickOnPersonalProfile();
        personalProfilePage.approveReceivedConnectionRequest();

        logoutPage.logoutSuccessfully();
        loginPage.loginUser(USERNAME_SENDER, PASSWORD_SENDER);
        homePage.searchUserByKnownUsername(USERNAME_RECEIVER);
        homePage.clickOnUserAfterSearch();
        homePage.disconnectFromAlreadyConnectedUser();

        homePage.validateDisconnectionFromAlreadyConnectedUser();

        logger.info(String.format("User with username %s, and password %s, approved connection request of user %s",
                USERNAME_RECEIVER, PASSWORD_RECEIVER, USERNAME_SENDER));
        logger.info(String.format("User with username %s, and password with %s, disconnected from the user with username %s.",
                USERNAME_SENDER, PASSWORD_SENDER, USERNAME_RECEIVER));

    }
}
