package ui.socialnetwork.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.socialnetwork.base.BaseTestSetup;

public class LoginTests extends BaseTestSetup {

    @Test
    @Tag("https://t-h-k-qa50-final-project.atlassian.net/jira/software/c/projects/FHKT/issues/FHKT-15")
    public void nonAdminUserAuthentication() {
        registerAndLoginUser();
        //ASSERT
        loginPage.assertAuthenticatedUser();
    }
    @Test
    @Tag("https://t-h-k-qa50-final-project.atlassian.net/jira/software/c/projects/FHKT/issues/FHKT-17")
    public void adminUserAuthenticationTest() {
        loginAdmin();
        //ASSERT
        loginPage.assertAdminAuthenticatedUser();
    }
    ///FEW MORE TEST
}
