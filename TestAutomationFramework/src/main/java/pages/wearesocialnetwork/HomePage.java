package pages.wearesocialnetwork;

import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

import static com.telerikacademy.testframework.Constants.*;
import static com.telerikacademy.testframework.Constants.USERNAME_RECEIVER_UI;

public class HomePage extends BaseSocialPage {
    Logger logger = Logger.getLogger("");

    public HomePage(WebDriver driver) {
        super(driver, "social.network.homepage");
    }


    public void validateAnonymousUserHomePageAccessAndLinksVisibility() {
        actions.assertElementPresent("//h1[text()='The Easiest Way to Hack the Crisis']");
        actions.assertElementPresent("nav.bar.brand");
        actions.assertElementPresent("register.button");
        actions.assertElementPresent("home.page.sign.in.button");
        actions.assertElementPresent("home.page.home.button");
        actions.assertElementPresent("home.page.latest.post.button");
        actions.assertElementPresent("home.page.about.us");
        actions.assertElementPresent("//button [@class='form-control btn btn-primary']");

        logger.info("HomePage successfully accesses without authentication with visibility of header and page links.");

    }


    public void validateUserCanScrollDownInHomePageToSpecificElement() {
        actions.scrollDownInPage("(//a[@class='nav-link' and contains(text(), 'REGISTER')])[2]");
        actions.waitForElementToBeClickableUntilTimeout("(//a[@class='nav-link' and contains(text(), 'REGISTER')])[2]", 5);
    }


    public void clickOnRegisterButton() {
        actions.waitForElementVisible("register.button");
        actions.clickElement("register.button");

        actions.waitForElementVisible("register.page.submitButton");
    }

    public void clickOnSignInButton() {
        actions.waitForElementVisible("home.page.sign.in.button");
        actions.clickElement("home.page.sign.in.button");

        actions.waitForElementVisible("login.submit.button");

    }

    public void clickOnHomeButton() {
        actions.waitForElementVisible("home.page.home.button");
        actions.clickElement("home.page.home.button");

    }

    public void clickOnLatestPostsButton() {
        actions.waitForElementVisible("home.page.latest.post.button");
        actions.clickElement("home.page.latest.post.button");

        actions.waitForElementVisible("latest.posts.message");

    }

    public void clickOnWeAreButton() {
        actions.waitForElementClickable("nav.bar.brand");
        actions.clickElement("nav.bar.brand");
    }

    public void clickOnAboutUsButton() {
        actions.waitForElementVisible("home.page.about.us");
        actions.clickElement("home.page.about.us");

        actions.waitForElementVisible("about.us.information.message");

    }

    public void clickOnUserSearchBar() {
        actions.waitForElementVisible("//button [@class='form-control btn btn-primary']");
        actions.clickElement("//button [@class='form-control btn btn-primary']");
        actions.waitForElementVisible("//p[@class='proile-rating']");

    }

    public void clickOnUserAfterSearch() {
        actions.waitForElementVisible("//a[@class='btn btn-primary']");
        actions.clickElement("//a[@class='btn btn-primary']");

    }

    public void sendConnectionToSearchedUser() {
        actions.waitForElementVisible("//input[@class='btn btn-primary']");
        actions.clickElement("//input[@class='btn btn-primary']");

    }

    public void clickOnPersonalProfile() {
        actions.waitForElementClickable("profile.personal.page.button");
        actions.clickElement("profile.personal.page.button");

    }

    public void disconnectFromAlreadyConnectedUser() {
        actions.waitForElementClickable("//input[@value='disconnect']");
        actions.clickElement("//input[@value='disconnect']");
    }

    public void validatePersonalProfileButton() {
        actions.assertElementVisible("profile.editProfile.page.button");

    }

    public void searchUserByProfession() {
        actions.waitForElementVisible("//input[@id='searchParam1']");
        actions.typeValueInField("Hairdresser", "//input[@id='searchParam1']");
        clickOnUserSearchBar();

    }

    public void searchUserByKnownUsername(String name) {
        actions.waitForElementVisible("//input[@id='searchParam2']");
        actions.typeValueInField(name, "//input[@id='searchParam2']");
        actions.clickElement("//button [@class='form-control btn btn-primary']");
    }

    public void validateUserSearchByProfession() {
        actions.assertElementPresent("//span[@class='position' and text()='Hairdresser']");
    }

    public void verifySuccessfulConnectionRequestMessage() {
        actions.waitForElementVisible("//div[text()='Good job! You have send friend request!']");
        actions.assertElementPresent("//div[text()='Good job! You have send friend request!']");

        logger.info(String.format("Username with %s, and password with %s, sent connection request.",
                USERNAME_SENDER_UI, PASSWORD_SENDER_UI));
        logger.info(String.format("Username with %s, and password with %s, received connection request.",
                USERNAME_RECEIVER_UI, PASSWORD_RECEIVER_UI));
    }

    public void validateSearchedUsernameInSearchResults(String firstName, String lastName) {
        actions.assertElementVisible("homepage.searchresult.username", firstName, lastName);
    }

    public void validateSearchUserByKnownUsername(String name) {
        actions.assertElementPresent(String.format("//h2[text()='%s']", name));
        logger.info("User with username 'Public Profile' is visible.");
    }

    public void validateDisconnectionFromAlreadyConnectedUser() {

        actions.waitForElementClickable("//input[@value='connect']");
        logger.info(String.format("User with username %s, and password %s, approved connection request of user %s",
                USERNAME_RECEIVER_UI, PASSWORD_RECEIVER_UI, USERNAME_SENDER_UI));
        logger.info(String.format("User with username %s, and password with %s, disconnected from the user with username %s.",
                USERNAME_SENDER_UI, PASSWORD_SENDER_UI, USERNAME_RECEIVER_UI));

    }

    public void validateSearchBarShowsUsers() {
        actions.assertElementPresent("//p[@class='proile-rating']");
    }

    public void validateAboutUsInformationDisplayed() {
        actions.assertElementPresent("about.us.information.message");

    }

    public void verifyScrollDownInHomePage() {
        actions.assertElementPresent("(//a[@class='nav-link' and contains(text(), 'REGISTER')])[2]");
        actions.waitForElementToBeClickableUntilTimeout("(//a[@class='nav-link' and contains(text(), 'REGISTER')])[2]", 3);
        logger.info("Scroll down action is successful 'Register' button is visible and clickable");
    }

    public void validateHomePageHeader(String key) {
        actions.assertElementVisible("weare.homepage.h1", key);
        logger.info("Scroll up action is successful 'The Easiest Way to Hack the Crisis' header is visible.");
    }

    public void validateLatestPostsDisplayed() {
        actions.assertElementPresent("latest.posts.message");
    }

    public void validateRegisterFormFullyDisplayed() {
        actions.assertElementPresent("join.our.community");
        actions.assertElementPresent("register.page.usernameField");
        actions.assertElementPresent("//input[@name='email']");
        actions.assertElementPresent("//input[@name='password']");
        actions.assertElementPresent("//input[@name='confirmPassword']");
        actions.assertElementPresent("//input[@value = 'Register']");
    }

    public void validateLoginFormDisplayed() {
        actions.assertElementPresent("login.page.message");
        actions.assertElementPresent("login.page.username");
    }

    public void clickOnGoToAdminZoneButton() {
        actions.waitForElementVisible("admin.zone.button");
        actions.clickElement("admin.zone.button");
    }

    public void clickOnViewAllUsersButton() {
        actions.waitForElementVisible("admin.zone.button");
        actions.clickElement("admin.zone.button");

        actions.waitForElementVisible("admin.view.all.users");
        actions.clickElement("admin.view.all.users");

    }

    public void verifyAdminViewAllUsers() {
        actions.assertElementPresent("admin.view.off.all.user");
    }
}
