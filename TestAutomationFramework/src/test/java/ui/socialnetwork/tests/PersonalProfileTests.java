package ui.socialnetwork.tests;

import org.junit.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class PersonalProfileTests extends BaseTestSetup {
    String firstName;
    String lastName;
    String email;

    @Test
    public void UpdateUserProfileWithFirstLastNameAndBirthdayTest() {

        loginSocial();

        personalProfilePage.enterPersonalProfile();
        firstName = personalProfilePage.generateFirstName();
        lastName = personalProfilePage.generateLastName();
        personalProfilePage.setFirstLastNamesAndBirthdate(firstName, lastName);

        //assert should be added

    }

    @Test//IN PROGRESS
    public void updateUserProfileEmailAddressTest() {

        loginSocial();

        personalProfilePage.enterPersonalProfile();
        email = personalProfilePage.generateEmail();
        personalProfilePage.updateUserProfileWithEmailAddress(email);
    }

    @Test
    public void updatePersonalJobTittleInformationTest() {
        loginSocial();

        personalProfilePage.enterPersonalProfile();
        personalProfilePage.updateJobSection();


        //assert should be added
    }

    @Test//IN PROGRESS
    public void updatePersonalSkillsInformationTest() {
        loginSocial();

        //update button is the same with jobTitleTes and the update is not successful
        personalProfilePage.enterPersonalProfile();
        personalProfilePage.updateSkillsSection();



    }
}
