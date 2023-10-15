package ui.socialnetwork.tests;

import api.socialnetwork.tests.UserControllerTest;
import apisocialnetwork.Utils;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

import static apisocialnetwork.Constants.RANDOM_EMAIL;
import static apisocialnetwork.Constants.SEARCHABLE_NAME;

public class HomePageTests extends BaseTestSetup {
    String homePageHeader = "The Easiest Way to Hack the Crisis";
    UserControllerTest userControllerTest = new UserControllerTest();

    @Test
    public void anonymousUserHomePageAccessAndLinksVisibilityTest() {
        //homePage.navigateToPage(); page is already being navigated
        homePage.validateAnonymousUserHomePageAccessAndLinksVisibility();

    }

    ///add homepage visibility for authenticated users.

    @Test
    public void anonymousUserCanScrollDownInHomePageTest() {

        actions.scrollDown(3000);
        homePage.verifyScrollDownInHomePage();

        actions.scrollUp(-3000);
        homePage.validateHomePageHeader(homePageHeader);

    }

    @Test
    public void anonymousUserRegisterFormDisplayWhenRegisterButtonClickedTest() {

        //homePage.navigateToPage();
        homePage.clickOnRegisterButton();

        //ASSERT
        homePage.validateRegisterFormFullyDisplayed();
    }

    @Test
    public void anonymousUserLoginFormDisplayWhenSignInButtonClickedTest() {

        //homePage.navigateToPage();
        homePage.clickOnSignInButton();

        //ASSERT
        homePage.validateLoginFormDisplayed();
    }

    @Test
    public void anonymousUserWeAreButtonNavigatesHomePageTest() {

        //homePage.navigateToPage();
        homePage.clickOnAboutUsButton();
        homePage.clickOnWeAreButton();

        homePage.validateHomePageHeader(homePageHeader);
    }

    @Test
    public void anonymousUserLatestPostsDisplayWhenLatestPostButtonClickedTest() {

        homePage.clickOnLatestPostsButton();

        //ASSERT
        homePage.validateLatestPostsDisplayed();
    }

    @Test
    public void anonymousUserAboutUsInformationDisplayedWhenAboutUsButtonClickedTest() {

        homePage.clickOnAboutUsButton();

        //ASSERT
        homePage.validateAboutUsInformationDisplayed();
    }

    @Test
    public void anonymousUserSearchAllUsersWhenSearchButtonClickedTest() {

        homePage.clickOnUserSearchBar();

        homePage.validateSearchBarShowsUsers();

    }

    @Test
    public void anonymousUserSearchUserByKnownUsernameTest() {
        SEARCHABLE_NAME = registerPage.generateUser();
        RANDOM_EMAIL = Utils.generateRandomEmail();
        System.out.println(SEARCHABLE_NAME);
        userControllerTest.updateUserProfileTest();

        homePage.searchUserByKnownUsername(SEARCHABLE_NAME + " randomLastName");

        homePage.validateSearchUserByKnownUsername(SEARCHABLE_NAME + " randomLastName");
    }

    @Test
    public void anonymousUserShowUsersByProfessionWhenSearchByProfessionTest() {

        homePage.searchUserByProfession();

        homePage.validateUserSearchByProfession();

    }
}

//    @Test
//    public void authenticatedUserLinks
//    //@Test authenticatedUserHomePagLinks Tests
//
//}

