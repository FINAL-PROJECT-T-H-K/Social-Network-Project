package ui.socialnetwork.tests;

import api.socialnetwork.tests.ConnectionControllerTest;
import apisocialnetwork.Utils;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class ConnectionTests extends BaseTestSetup {
    private static String USERNAME_SENDER;
    private static String PASSWORD_SENDER;
    private static String PASSWORD_RECEIVER;
    private static String USERNAME_RECEIVER;
    Utils utils = new Utils();
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
    }
    @Test
    public void approveAlreadySentConnectionRequestTest() {

        sendingConnectionRequestToAnotherUserTest();
        logoutPage.logoutSuccessfully();
        loginPage.loginUser(USERNAME_RECEIVER, PASSWORD_RECEIVER);
        homePage.clickOnPersonalProfile();
        personalProfilePage.approveReceivedConnectionRequest();

        personalProfilePage.validateReceivedConnectionRequestApproved();

    }
    //@Test Disconnect from connected user
}
