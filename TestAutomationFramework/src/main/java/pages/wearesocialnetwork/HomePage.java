package pages.wearesocialnetwork;

import org.openqa.selenium.WebDriver;

public class HomePage extends BaseSocialPage {
    public HomePage(WebDriver driver) {
        super(driver, "social.network.homepage");
    }


    public void validateHomePageAccessAndLinksVisibility() {
        actions.assertElementPresent("//h1[text()='The Easiest Way to Hack the Crisis']");
        actions.assertElementPresent("nav.bar.brand");
        actions.assertElementPresent("register.button");
        actions.assertElementPresent("home.page.sign.in.button");
        actions.assertElementPresent("home.page.home.button");
        actions.assertElementPresent("home.page.latest.post.button");
        actions.assertElementPresent("home.page.about.us");
        actions.assertElementPresent("//button [@class='form-control btn btn-primary']");

        System.out.println("HomePage successfully accesses without authentication with visibility of header and page links.");

    }

    public void validateUserCanScrollDownInHomePageToSpecificElement() {
        actions.scrollDownInPage("(//a[@class='nav-link' and contains(text(), 'REGISTER')])[2]");
        actions.waitForElementToBeClickableUntilTimeout("(//a[@class='nav-link' and contains(text(), 'REGISTER')])[2]", 5);
    }

    public void scrollDownInHomePage() {
        actions.scrollDown(3000);

    }

    public void scrollUpInHomePage() {
        actions.scrollUp(-3000);

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

    public void searchUserByProfession() {
        actions.waitForElementVisible("//input[@id='searchParam1']");
        actions.typeValueInField("Hairdresser", "//input[@id='searchParam1']");
        clickOnUserSearchBar();

    }

    public void searchUserByKnownUsername() {
        actions.waitForElementVisible("//input[@id='searchParam2']");
        actions.typeValueInField("Public Profile", "//input[@id='searchParam2']");
        actions.clickElement("//button [@class='form-control btn btn-primary']");
    }

    public void validateUserSearchByProfession() {
        actions.assertElementPresent("//span[@class='position' and text()='Hairdresser']");

    }

    public void validateSearchUserByKnownUsername() {
        actions.assertElementPresent("//h2[text()='Public Profile']");
        System.out.println("User with username 'Public Profile' is visible.");

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
        System.out.println("Scroll down action is successful 'Register' button is visible and clickable");
    }

    public void verifyScrollUpInHomePage() {
        actions.assertElementPresent("//h1[text()='The Easiest Way to Hack the Crisis']");
        System.out.println("Scroll up action is successful 'The Easiest Way to Hack the Crisis' header is visible.");
    }

    public void validateLatestPostsDisplayed() {
        actions.assertElementPresent("latest.posts.message");
    }

    public void validateWEareButtonNavigatesHomePage() {
        actions.assertElementPresent("nav.bar.brand");
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


}
