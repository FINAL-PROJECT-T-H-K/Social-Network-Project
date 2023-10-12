package apisocialnetwork;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

import java.util.Locale;

public class Utils {

    public static String generateRandomEmail() {
        // Create a random string generator
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z')
                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                .build();

        // Generate a random email address
        StringBuilder email = new StringBuilder();
        email.append(generator.generate(10)); // Generate 10 random characters
        email.append("@example.com");

        return email.toString();
    }

    public static String generateUniqueUsername() {
        return "Username" + RandomStringUtils.randomAlphabetic(8);
    }

    // Helper method to generate a unique password
    public static String generateUniquePassword() {
        return "Password" + RandomStringUtils.randomAlphabetic(8);
    }

    public static String generateFakeServiceUsername() {

        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());


        String randomUsername = fakeValuesService.bothify("Username?????");
        return randomUsername;
    }

    public static String generateFakeServicePassword() {

        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String randomPassword = fakeValuesService.bothify("Password?????");
        return randomPassword;
    }

}
