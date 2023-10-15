package ui.socialnetwork.tests;

import apisocialnetwork.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

import java.util.logging.Logger;

public class PersonalProfileTests extends BaseTestSetup {
    @BeforeEach
    public void setupUser() {
        registerAndLoginUser();
    }

    Logger logger = Logger.getLogger("");
    String firstName = "first";
    String lastName = "last";
    String email = "";
    String personalInfo;

    @Test
    public void updateUserProfileWithMustHaveFieldsTest() {
        personalProfilePage.enterPersonalProfile();
        firstName += Utils.generateFirstName();
        lastName += Utils.generateLastName();
        personalProfilePage.setFirstLastNamesAndBirthdate(firstName, lastName);
        personalProfilePage.clickOnUpdateProfileButton();
        personalProfilePage.backToProfileInfo();

        //assert
        personalProfilePage.assertFirstLastNamesUpdated(firstName,lastName);


    }

    @Test
    public void updateUserProfileWithFirstLastNameBirthdayGenderEmailPublicInfoCityTest() {
        personalProfilePage.enterPersonalProfile();
        firstName += Utils.generateFirstName();
        lastName += Utils.generateLastName();
        personalProfilePage.setFirstLastNamesAndBirthdate(firstName, lastName);
        email = personalProfilePage.generateRandomEmail();
        personalProfilePage.updateUserProfileWithEmailAddress(email);
        personalProfilePage.updateUserProfileWithGender();
        personalInfo += personalProfilePage.generateInfo();
        personalProfilePage.updateUserPublicInfo(personalInfo);
        personalProfilePage.updateCity();
        personalProfilePage.clickOnUpdateProfileButton();
        personalProfilePage.backToProfileInfo();

        //assert
        personalProfilePage.assertFirstLastNamesUpdated(firstName,lastName);
        personalProfilePage.assertEmailUpdated(email);

    }


    @Test
    public void updatePersonalJobTittleInformationTest() {
        personalProfilePage.enterPersonalProfile();
        personalProfilePage.updateJobSection();

        //assert
        personalProfilePage.assertJobTitleUpdated();
    }

    @Test
    public void updatePersonalSkillsInformationTest() {
        personalProfilePage.enterPersonalProfile();
        personalProfilePage.updateSkillsSection();

        //assert
        personalProfilePage.assertAvailabilityUpdated();

    }
}
