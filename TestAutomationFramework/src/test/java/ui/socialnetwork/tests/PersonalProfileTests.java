package ui.socialnetwork.tests;

import api.socialnetwork.tests.ConnectionControllerTest;
import api.socialnetwork.tests.UserControllerTest;
import apisocialnetwork.Utils;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

import java.util.logging.Logger;

import static apisocialnetwork.Constants.*;

public class PersonalProfileTests extends BaseTestSetup {

    public static String USERNAME_SENDER;
    public static String PASSWORD_SENDER;
    public static String PASSWORD_RECEIVER;
    public static String USERNAME_RECEIVER;

    Utils utils = new Utils();
    ConnectionControllerTest connectionControllerTest = new ConnectionControllerTest();
    Logger logger = Logger.getLogger("");
    String firstName = "first";
    String lastName = "last";
    String email = "";
    String personalInfo;


    @Test
    public void updateUserProfileWithMustHaveFieldsTest() {

        loginUser();
        personalProfilePage.enterPersonalProfile();
        firstName += Utils.generateFirstName();
        lastName += Utils.generateLastName();
        personalProfilePage.setFirstLastNamesAndBirthdate(firstName, lastName);
        personalProfilePage.clickOnUpdateProfileButton();
        personalProfilePage.backToProfileInfo();

        //assert
        personalProfilePage.assertProfilesInformationUpdated();
        //assert should be added
        personalProfilePage.validateFirstname(firstName);

    }

    @Test
    public void updateUserProfileWithFirstLastNameBirthdayGenderEmailPublicInfoCityTest() {

        loginUser();
        personalProfilePage.enterPersonalProfile();
        firstName += personalProfilePage.generateFirstName();
        lastName += personalProfilePage.generateLastName();
        personalProfilePage.setFirstLastNamesAndBirthdate(firstName, lastName);
        email = personalProfilePage.generateRandomEmail();
        email = Utils.generateEmail();
        personalProfilePage.updateUserProfileWithEmailAddress(email);
        personalProfilePage.updateUserProfileWithGender();
        personalInfo += personalProfilePage.generateInfo();
        personalProfilePage.updateUserPublicInfo(personalInfo);
        personalProfilePage.updateCity();
        personalProfilePage.clickOnUpdateProfileButton();

        personalProfilePage.assertProfilesInformationUpdated();
    }


    @Test
    public void updatePersonalJobTittleInformationTest() {
        loginUser();

        personalProfilePage.enterPersonalProfile();
        personalProfilePage.updateJobSection();

        //assert
        personalProfilePage.assertJobTitleUpdated();
    }

    @Test
    public void updatePersonalSkillsInformationTest() {
        loginUser();

        personalProfilePage.enterPersonalProfile();
        personalProfilePage.updateSkillsSection();

        //assert
        personalProfilePage.assertAvailabilityUpdated();

    }
    @Test
    public void sendingConnectionRequestToAnotherUserTest(){
        USERNAME_RECEIVER = Utils.generateUniqueUsername();
        PASSWORD_RECEIVER = Utils.generateUniquePassword();
        loginUserWithParams(USERNAME_RECEIVER, PASSWORD_RECEIVER);
        personalProfilePage.enterPersonalProfile();
        personalProfilePage.setFirstLastNamesAndBirthdate(USERNAME_RECEIVER, USERNAME_RECEIVER);
        personalProfilePage.clickOnUpdateProfileButton();
        personalProfilePage.backToProfileInfo();
        logoutPage.logoutSuccessfully();

        USERNAME_SENDER = Utils.generateUniqueUsername();
        PASSWORD_SENDER = Utils.generateUniquePassword();
        loginUserWithParams(USERNAME_SENDER, PASSWORD_SENDER);
        homePage.searchUserByKnownUsername(USERNAME_RECEIVER);
        homePage.clickOnUserAfterSearch();
        homePage.sendConnectionToSearchedUser();

        homePage.validateSearchedUsernameInSearchResults(USERNAME_RECEIVER, USERNAME_RECEIVER);
        homePage.verifySuccessfulConnectionRequestMessage();

    }

    public void searchUserByKnownUsernameTest() {
        UserControllerTest userControllerTest = new UserControllerTest();

        SEARCHABLE_NAME = registerPage.generateUser();
        RANDOM_EMAIL = Utils.generateRandomEmail();
        System.out.println(SEARCHABLE_NAME);
        userControllerTest.updateUserProfileTest();


        homePage.searchUserByKnownUsername(SEARCHABLE_NAME + " randomLastName");

        homePage.validateSearchUserByKnownUsername(SEARCHABLE_NAME + " randomLastName");
    }
}
