package apisocialnetwork;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.json.JSONException;
import org.json.JSONObject;

public class Helper {

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

    public static boolean isValid(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

}
