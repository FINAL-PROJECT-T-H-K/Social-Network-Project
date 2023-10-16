package pages.wearesocialnetwork;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalProfilePage extends BaseSocialPage {

    String firstName;
    String lastName;
    String email;
    String personalInfo;

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

    }

    public void updateUserProfileWithEmailAddress(String email) {
        actions.deleteEmailFied();
        actions.waitForElementVisible("profile.email.address");
        actions.typeValueInField(email, "profile.email.address");
    }

    public void updateUserProfileWithGender() {
        actions.waitForElementVisible("profile.inputGender");
        actions.clickElement("profile.inputGender");
    }

    public void enterPersonalProfile() {
        actions.waitForElementVisible("profile.personal.page.button");
        actions.clickElement("profile.personal.page.button");
        actions.waitForElementClickable("profile.editProfile.page.button");
        actions.clickElement("profile.editProfile.page.button");

    }

    public void clickOnUpdateProfileButton() {
        actions.waitForElementClickable("profile.update.personal.profile.button");
        actions.clickElement("profile.update.personal.profile.button");
    }
    public void clickOnNewFriendRequests(){
        actions.waitForElementClickable("personal.profile.new.request.button");
        actions.clickElement("personal.profile.new.request.button");
    }
    public void clickOnConnect() {
        actions.waitForElementClickable("//input[@class='btn btn-primary' and @value='connect']");
        actions.clickElement("//input[@class='btn btn-primary' and @value='connect']");

    }

    public void backToProfileInfo() {
        actions.waitForElementClickable("profile.back.to.profile");
        actions.clickElement("profile.back.to.profile");
    }

    public void approveReceivedConnectionRequest() {
        actions.waitForElementVisible("//input[@class='btn btn-primary']");
        actions.clickElement("//input[@class='btn btn-primary']");
        actions.waitForElementVisible("//input[@class='btn btn-primary py-2']");
        actions.clickElement("//input[@class='btn btn-primary py-2']");
    }

    public void validateReceivedConnectionRequestApproved() {
        actions.assertElementVisible("//h3[@class='mb-3 bread']");

    }
    public void assertEmailUpdated(String email) {
        actions.assertEmailFieldOnProfilePage(email);
    }
    public void assertFirstLastNamesUpdated(String firstName, String lastName) {
        actions.assertFirstLastNamesFieldOnProfilePage(firstName,lastName);
    }
    public void updateJobSection() {
        actions.waitForElementClickable("profile.job.tittle");
        actions.clickElement("profile.job.tittle");
        actions.waitForElementClickable("profile.update.job.tittle.button");
        actions.clickElement("profile.update.job.tittle.button");

    }

    public void updateSkillsSection() {
        actions.waitForElementClickable("profile.skills.tittle");
        actions.typeValueInField("Quality Assurance", "profile.skills.tittle");
        actions.waitForElementClickable("profile.skills.tittle");
        actions.typeValueInField("16", "profile.update.availability");
        actions.waitForElementClickable("profile.update.skills.button");
        actions.clickElement("profile.update.skills.button");
    }

    public void updateUserPublicInfo(String info) {
        actions.waitForElementClickable("profile.input.info");
        actions.typeValueInField(info, "profile.input.info");
    }

    public void updateCity() {
        actions.waitForElementClickable("profile.input.city");
        actions.clickElement("profile.input.city");
    }

    public void assertAvailabilityUpdated() {
        String xpath = "//span[text()='Quality Assurance']";
        actions.waitForElementVisible(xpath);
        String spanText = driver.findElement(By.xpath(xpath)).getText();
        Assertions.assertEquals("Quality Assurance", spanText, "Expected text does not match the actual text.");
    }
    public void assertAvailability() {
        actions.assertElementPresent("//h3[@class='heading-sidebar']");
    }



    public void assertJobTitleUpdated() {
        String spanText = driver.findElement(By.xpath("//span[text()='Translator']")).getText();
        Assertions.assertFalse(spanText.isEmpty(), "The text inside the span is not empty.");


    }




    public String generateFirstName() {
        firstName += RandomStringUtils.randomAlphabetic(8);
        return firstName;
    }

    public String generateLastName() {
        lastName += RandomStringUtils.randomAlphabetic(8);
        return lastName;
    }

    public String generateRandomEmail() {
        String username = RandomStringUtils.randomAlphabetic(10);
        return username + "@gmail.com";
    }

    public String generateInfo() {
        personalInfo += RandomStringUtils.randomAlphabetic(25);
        return personalInfo;
    }


}

