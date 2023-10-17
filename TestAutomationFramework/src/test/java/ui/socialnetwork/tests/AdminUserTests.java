package ui.socialnetwork.tests;

import apisocialnetwork.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;
import static com.telerikacademy.testframework.Constants.*;

public class AdminUserTests extends BaseTestSetup {

    @BeforeEach
    public void setupLogin() {
        adminUsername += registerPage.generateUser();
        password += registerPage.generatePassword();
        registerUser(adminUsername, password);
        loginUser(adminUsername,password);
    }

    @Test
    @Tag("FHKT-263")
    public void adminUserViewAllUsersTest() {
        homePage.clickOnGoToAdminZoneButton();
        homePage.clickOnViewAllUsersButton();
        //assert
        homePage.verifyAdminViewAllUsers();
    }

    @Test
    @Tag("FHKT-267")
    public void adminUserCreatePublicPostsTest() {
        postPage.createPublicPost();
        //Assert
        postPage.verifyPostCreated();
        postPage.verifyPublicPostCreated();

    }

    @Test
    @Tag("FHKT-268")
    public void adminUserCreatePrivatePostsTest() {
        postPage.createPrivatePost();
        //Assert
        postPage.verifyPostCreated();
        postPage.verifyPrivatePostCreated();

    }

    @Test
    @Tag("FHKT-269")
    public void adminUserLikePostWhenClickLikeButtonTest() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        homePage.clickOnLatestPostsButton();
        postPage.likePublicPost();
        //assert
        postPage.validatePostIsLiked();

    }

    @Test
    @Tag("FHKT-270")
    public void adminUserDislikePostWhenClickLikeButtonTest() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        homePage.clickOnLatestPostsButton();
        postPage.likePublicPost();
        //assert
        postPage.validateTopicIsUnliked();
    }

    @Test
    @Tag("FHKT-58")
    public void adminUserEditPostTest() {
        homePage.clickOnLatestPostsButton();
        commentPage.clickOnExploreThePost();
        postPage.userEditPost();
        //assert
        postPage.validatePostIsEdited();
    }
    @Test
    @Tag("FHKT-271")
    public void adminUserCreateCommentUnderThePostTests() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentText = commentPage.generateRandomComment();
        commentPage.createCommentUnderPost(commentText);
        //assert
        commentPage.verifyFirstCommentCreated();
    }

    @Test
    @Tag("FHKT-273")
    public void adminUserLikeCommentUnderThePostTests() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentText = commentPage.generateRandomComment();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();
        commentPage.userLikeCommentUnderThePost();
        //assert
        commentPage.validateTopicIsLiked();
    }

    @Test
    @Tag("FHKT-274")
    public void adminUserDislikeCommentUnderThePostTests() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentText = commentPage.generateRandomComment();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();
        commentPage.userDeleteCommentUnderThePost();
        //assert
        commentPage.verifyCommentDeleted();
    }

    @Test
    @Tag("FHKT-62")
    public void adminUserEditCommentUnderThePostTests() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentText = commentPage.generateRandomComment();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();
        editedComment = commentPage.generateRandomEditComment();
        commentPage.userEditCommentUnderThePost(editedComment);
        //assert
        commentPage.verifyCommentЕdited();
    }

    @Test
    @Tag("FHKT-64")
    public void adminUserDeleteCommentUnderThePostTests() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentText = commentPage.generateRandomComment();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();
        commentPage.userDeleteCommentUnderThePost();
        //assert
        commentPage.verifyCommentDeleted();
    }

    @Test
    @Tag("FHKT-262")
    public void adminUserUpdateUserProfileWithFirstLastNameBirthdayGenderEmailPublicInfoCityTest() {
        personalProfilePage.enterPersonalProfile();
        firstName += Utils.generateFirstName();
        lastName += Utils.generateLastName();
        personalProfilePage.setFirstLastNamesAndBirthdate(firstName, lastName);
        email = personalProfilePage.generateRandomEmail();
        personalProfilePage.updateUserProfileWithEmailAddress(email);
        personalProfilePage.updateUserProfileWithGender();
        personalInfo += personalProfilePage.generateInfo();
        personalProfilePage.updateUserPublicInfo(personalInfo);
        personalProfilePage.updateCity();
        personalProfilePage.clickOnUpdateProfileButton();
        personalProfilePage.backToProfileInfo();

        //assert
        personalProfilePage.assertFirstLastNamesUpdated(firstName, lastName);
        personalProfilePage.assertEmailUpdated(email);

    }
}
