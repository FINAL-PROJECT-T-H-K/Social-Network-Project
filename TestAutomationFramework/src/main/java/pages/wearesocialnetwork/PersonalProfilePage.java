package pages.wearesocialnetwork;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

public class PersonalProfilePage extends BaseSocialPage {
    String firstName;
    String lastName;
    String email;

    public PersonalProfilePage(WebDriver driver) {
        super(driver, "social.network.homepage");
    }

    public void setFirstLastNamesAndBirthdate(String firstName, String lastName) {

        actions.waitForElementVisible("profile.first.name");
        actions.typeValueInField(firstName, "profile.first.name");

        actions.waitForElementVisible("profile.last.name");
        actions.typeValueInField(lastName, "profile.last.name");

        actions.waitForElementVisible("profile.birthday");
        actions.typeValueInField("03/05/1987", "profile.birthday");

        actions.waitForElementClickable("profile.update.personal.profile.button");
        actions.clickElement("profile.update.personal.profile.button");
    }

    public void updateUserProfileWithEmailAddress(String email) {

        actions.waitForElementVisible("profile.email.address");
        actions.typeValueInField(email, "profile.email.address");

        actions.waitForElementClickable("profile.update.personal.profile.button");
        actions.clickElement("profile.update.personal.profile.button");
    }

    public void enterPersonalProfile() {
        actions.waitForElementVisible("profile.personal.page.button");
        actions.clickElement("profile.personal.page.button");

        actions.waitForElementClickable("profile.editProfile.page.button");
        actions.clickElement("profile.editProfile.page.button");

    }

    public String generateFirstName() {
        firstName += RandomStringUtils.randomAlphabetic(8);
        return firstName;
    }

    public String generateLastName() {
        lastName += RandomStringUtils.randomAlphabetic(8);
        return lastName;
    }

    public String generateEmail() {
        email += RandomStringUtils.randomAlphabetic(2);
        return email;
    }

    public void assertProfilesInformationUpdated() {
        //still don't have

    }

    public void updateJobSection() {
        actions.waitForElementClickable("profile.job.tittle");
        actions.clickElement("profile.job.tittle");

        actions.waitForElementClickable("profile.update.job.button");
        actions.clickElement("profile.update.job.button");

    }

    public void updateSkillsSection() {
        actions.waitForElementClickable("profile.skills.tittle");
        actions.typeValueInField("Quality Assurance", "profile.skills.tittle");

        actions.waitForElementClickable("profile.job.tittle");
        actions.clickElement("profile.updateSkills.button");

    }

}
