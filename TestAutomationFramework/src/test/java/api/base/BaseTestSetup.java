package api.base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeSuite;

import static apiSocialNetwork.Constants.PASSWORD;
import static apiSocialNetwork.Constants.USERNAME;
import static apiSocialNetwork.Endpoints.BASE_URL;
import static io.restassured.RestAssured.baseURI;

public class BaseTestSetup {
    public static String uniqueName;
    public static String ID;

    @BeforeSuite
    public void initialSetup() {
        baseURI = BASE_URL;
        uniqueName = RandomStringUtils.randomAlphabetic(10);

    }
   public static RequestSpecification getApplicationAuthentication() {
       return RestAssured
               .given()
               .auth()
               .basic(USERNAME,PASSWORD)
               .header("Accept","application/json");
    }
}

