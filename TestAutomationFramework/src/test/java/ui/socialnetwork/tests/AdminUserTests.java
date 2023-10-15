package ui.socialnetwork.tests;

import apisocialnetwork.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

import java.util.logging.Logger;

public class AdminUserTests extends BaseTestSetup {
    Logger logger = Logger.getLogger("");
    String firstName = "first";
    String lastName = "last";
    String email = "";
    String personalInfo;
    public String commentText;
    public String editedComment;
    @BeforeEach
    public void setupLogin (){
        loginAdmin();
    }

    @Test
    public void adminUserViewAllUsersTest() {
        homePage.clickOnGoToAdminZoneButton();
        homePage.clickOnViewAllUsersButton();
        //assert
        homePage.verifyAdminViewAllUsers();
    }

    @Test
    public void adminUserCreatePublicPostsTest() {
        postPage.createPublicPost();
        //Assert
        postPage.verifyPostCreated();
        postPage.verifyPublicPostCreated();

    }

    @Test
    public void adminUserCreatePrivatePostsTest() {
        postPage.createPrivatePost();
        //Assert
        postPage.verifyPostCreated();
        postPage.verifyPrivatePostCreated();

    }

    @Test
    public void adminUserLikePostWhenClickLikeButtonTest() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        homePage.clickOnLatestPostsButton();
        postPage.likePublicPost();

        //assert
        postPage.validatePostIsLiked();

    }

    @Test
    public void adminUserDislikePostWhenClickLikeButtonTest() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        homePage.clickOnLatestPostsButton();
        postPage.likePublicPost();

        //assert
        postPage.validateTopicIsUnliked();


    }

    @Test
    public void adminUserEditPostTest() {
        homePage.clickOnLatestPostsButton();
        commentPage.clickOnExploreThePost();
        postPage.userEditPost();
        //assert
        postPage.validatePostIsEdited();
    }

    /////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void adminUserCreateCommentUnderThePostTests() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentText = commentPage.generateRandomComment();
        commentPage.createCommentUnderPost(commentText);
        //assert
        commentPage.verifyCommentCreated();
    }

    @Test
    public void likeCommentUnderThePostTests() {
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
    public void dislikeCommentUnderThePostTests() {
        postPage.createPublicPost();
        homePage.clickOnHomeButton();
        postPage.clickOnTheRecentPost();
        commentText = commentPage.generateRandomComment();
        commentPage.createCommentUnderPost(commentText);
        commentPage.clickOnShowCommentsUnderThePost();
        commentPage.userLikeCommentUnderThePost();
        //assert
        commentPage.validateTopicIsUnliked();
    }

    @Test
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
    public void deleteCommentUnderThePostTests() {
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
    public void adminUserUpdateProfileWithFirstLastNameBirthdayGenderEmailPublicInfoCityTest() {
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
        personalProfilePage.assertFirstLastNamesUpdated(firstName,lastName);
        personalProfilePage.assertEmailUpdated(email);

    }
}
