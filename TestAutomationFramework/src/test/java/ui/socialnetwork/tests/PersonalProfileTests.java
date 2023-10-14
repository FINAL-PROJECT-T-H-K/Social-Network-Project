package ui.socialnetwork.tests;

import org.junit.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class PersonalProfileTests extends BaseTestSetup {
    String firstName;
    String lastName;
    String email;


    //IN PROGRESS
    @Test
    public void UpdateUserProfileWithFirstLastNameAndBirthdayTest() {

        loginUser();

        personalProfilePage.enterPersonalProfile();
        firstName = personalProfilePage.generateFirstName();
        lastName = personalProfilePage.generateLastName();
        personalProfilePage.setFirstLastNamesAndBirthdate(firstName, lastName);

        //assert should be added

    }

    @Test//IN PROGRESS
    public void updateUserProfileEmailAddressTest() {

        loginUser();

        personalProfilePage.enterPersonalProfile();
        email = personalProfilePage.generateEmail();
        personalProfilePage.updateUserProfileWithEmailAddress(email);
    }

    @Test
    public void updatePersonalJobTittleInformationTest() {
        loginUser();

        personalProfilePage.enterPersonalProfile();
        personalProfilePage.updateJobSection();


        //assert should be added
    }

    @Test//IN PROGRESS
    public void updatePersonalSkillsInformationTest() {
        loginUser();

        //update button is the same with jobTitleTes and the update is not successful
        personalProfilePage.enterPersonalProfile();
        personalProfilePage.updateSkillsSection();



    }
}
