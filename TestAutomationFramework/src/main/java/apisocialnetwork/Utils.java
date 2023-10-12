package apisocialnetwork;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import static apisocialnetwork.Constants.*;
import static apisocialnetwork.Constants.RANDOM_EMAIL;

public class Utils {

    public static String generateRandomEmail() {
        // Create a random string gnrtr
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

    public static boolean isValid(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    public static void fakeValueGenerator(String title1, String title2) {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        RANDOM_JOB_TITLE_FIRST = fakeValuesService.bothify(title1);
        RANDOM_JOB_TITLE = fakeValuesService.bothify(title2);
    }

    public static void generateRandomConstants(String usernamePattern, String passwordPattern,
                                               String uniqueNamePattern, String skillDescriptionPattern,
                                               String emailPattern) {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        USERNAME = fakeValuesService.bothify(usernamePattern);
        PASSWORD = fakeValuesService.bothify(passwordPattern);
        UNIQUE_NAME = fakeValuesService.bothify(uniqueNamePattern);
        SKILL_DESCRIPTION = fakeValuesService.bothify(skillDescriptionPattern);
        SKILL_DESCRIPTION_EDITED = SKILL_DESCRIPTION + UNIQUE_NAME;
        RANDOM_EMAIL = fakeValuesService.bothify(emailPattern);
    }


}
