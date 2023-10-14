package pages.wearesocialnetwork;

import org.openqa.selenium.WebDriver;

public class HomePage extends BaseSocialPage {
    public HomePage(WebDriver driver) {
        super(driver, "social.network.homepage");
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

    public void searchUserByProfession (){
        actions.waitForElementVisible("//input[@id='searchParam1']");
        actions.typeValueInField("Hairdresser","//input[@id='searchParam1']");
        clickOnUserSearchBar();

    }

    public void validateHomePageAccessAndLinksVisibility (){
        actions.waitForElementVisible("//h1[text()='The Easiest Way to Hack the Crisis']");
        actions.waitForElementVisible("nav.bar.brand");
        actions.waitForElementVisible("register.button");
        actions.waitForElementVisible("home.page.sign.in.button");
        actions.waitForElementVisible("home.page.home.button");
        actions.waitForElementVisible("home.page.latest.post.button");
        actions.waitForElementVisible("home.page.about.us");
        actions.waitForElementVisible("//button [@class='form-control btn btn-primary']");



    }
    public void validateUserSearchByProfession(){
        actions.assertElementPresent("//span[@class='position' and text()='Hairdresser']");

    }

    public void validateSearchBarShowsUsers(){
        actions.assertElementPresent("//p[@class='proile-rating']");
    }

    public void validateAboutUsInformationDisplayed() {
        actions.assertElementPresent("about.us.information.message");

    }

    public void validateLatestPostsDisplayed() {
        actions.assertElementPresent("latest.posts.message");
    }

    public void validateWEareButtonNavigatesHomePage() {
        actions.assertElementPresent("nav.bar.brand");
    }

    public void validateRegisterFormDisplayed() {
        actions.assertElementPresent("join.our.community");
        actions.assertElementPresent("register.page.usernameField");
    }

    public void validateLoginFormDisplayed() {
        actions.assertElementPresent("login.page.message");
        actions.assertElementPresent("login.page.username");
    }


}
