package ui.socialnetwork.tests;

import org.junit.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class HomePageTests extends BaseTestSetup {

    @Test
    public void homePageAccessAndLinksVisibilityTest() {
        homePage.navigateToPage();

        homePage.validateHomePageAccessAndLinksVisibility();

    }

    @Test
    public void userCanScrollDownInHomePageTest() throws InterruptedException {

        homePage.scrollDownInHomePage();
        homePage.verifyScrollDownInHomePage();

        homePage.scrollUpInHomePage();
        homePage.verifyScrollUpInHomePage();

    }

    @Test
    public void registerFormDisplayWhenRegisterButtonClickedTest() {

        homePage.navigateToPage();
        homePage.clickOnRegisterButton();

        //ASSERT
        homePage.validateRegisterFormDisplayed();
    }

    @Test
    public void loginFormDisplayWhenSignInButtonClickedTest() {

        homePage.navigateToPage();
        homePage.clickOnSignInButton();

        //ASSERT
        homePage.validateLoginFormDisplayed();
    }

    @Test
    public void weAreButtonNavigatesHomePageTest() {

        homePage.navigateToPage();
        homePage.clickOnHomeButton();

        //ASSERT
        homePage.validateWEareButtonNavigatesHomePage();
    }

    @Test
    public void latestPostsDisplayWhenLatestPostButtonClickedTest() {
        homePage.navigateToPage();
        homePage.clickOnLatestPostsButton();

        //ASSERT
        homePage.validateLatestPostsDisplayed();
    }

    @Test
    public void aboutUsInformationDisplayedWhenAboutUsButtonClickedTest() {
        homePage.navigateToPage();
        homePage.clickOnAboutUsButton();

        //ASSERT
        homePage.validateAboutUsInformationDisplayed();
    }

    @Test
    public void searchAllUsersWhenSearchButtonClickedTest() {
        homePage.navigateToPage();
        homePage.clickOnUserSearchBar();

        homePage.validateSearchBarShowsUsers();

    }

    @Test
    public void searchUserByKnownUsernameTest(){
        homePage.navigateToPage();
        homePage.searchUserByKnownUsername();

        homePage.validateSearchUserByKnownUsername();
    }

    @Test
    public void showUsersByProfessionWhenSearchByProfessionTest() {
        homePage.navigateToPage();
        homePage.searchUserByProfession();

        homePage.validateUserSearchByProfession();

    }


}

