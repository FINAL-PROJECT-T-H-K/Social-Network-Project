package ui.socialnetwork.tests;

import org.junit.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class HomePageTests extends BaseTestSetup {
    @Test
    public void successAccessRegisterFormTest() {

        homePage.navigateToPage();
        homePage.clickOnRegisterButton();

        //ASSERT
        homePage.validateRegisterFormDisplayed();
    }

    @Test
    public void successAccessLoginFormTest() {

        homePage.navigateToPage();
        homePage.clickOnSignInButton();

        //ASSERT
        homePage.validateLoginFormDisplayed();
    }

    @Test
    public void successAccessHomeButtonTest() {

        homePage.navigateToPage();
        homePage.clickOnHomeButton();

        //ASSERT
        homePage.validateHomeButtonDisplayed();
    }

    @Test
    public void successAccessLatestPostsButtonTest() {
        homePage.navigateToPage();
        homePage.clickOnLatestPostsButton();

        //ASSERT
        homePage.validateLatestPostsDisplayed();
    }

    @Test
    public void successAccessAboutUsButtonTest() {
        homePage.navigateToPage();
        homePage.clickOnAboutUsButton();

        //ASSERT
        homePage.validateAboutUsInformationDisplayed();
    }
}

