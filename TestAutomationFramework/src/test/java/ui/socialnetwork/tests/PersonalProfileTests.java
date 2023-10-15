package ui.socialnetwork.tests;

import api.socialnetwork.tests.ConnectionControllerTest;
import apisocialnetwork.Utils;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

import java.util.logging.Logger;

public class PersonalProfileTests extends BaseTestSetup {

    Logger logger = Logger.getLogger("");
    String firstName = "first";
    String lastName = "last";
    String email = "";
    String personalInfo;

    @Test
    public void updateUserProfileWithMustHaveFieldsTest() {

        registerAndLoginUser();
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

        registerAndLoginUser();
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
        registerAndLoginUser();

        personalProfilePage.enterPersonalProfile();
        personalProfilePage.updateJobSection();

        //assert
        personalProfilePage.assertJobTitleUpdated();
    }

    @Test
    public void updatePersonalSkillsInformationTest() {
        registerAndLoginUser();

        personalProfilePage.enterPersonalProfile();
        personalProfilePage.updateSkillsSection();

        //assert
        personalProfilePage.assertAvailabilityUpdated();

    }
}
