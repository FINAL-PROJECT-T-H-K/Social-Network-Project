package ui.socialnetwork.tests;

import apisocialnetwork.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

import java.util.logging.Logger;

import static com.telerikacademy.testframework.Constants.*;

public class PersonalProfileTests extends BaseTestSetup {

    @BeforeEach
    public void setupUser() {
        registerAndLoginUser();
    }
    Logger logger = Logger.getLogger("");

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
        personalProfilePage.assertFirstLastNamesUpdated(firstName, lastName);

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
        personalProfilePage.assertFirstLastNamesUpdated(firstName, lastName);
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

        //
        personalProfilePage.assertAvailability();
        personalProfilePage.assertAvailabilityUpdated();

    }


}
