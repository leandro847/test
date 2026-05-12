package healthtracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple authentication manager that reads and writes credentials from/to data.json.
 * The HealthTracker data.json file uses an array of user objects with username, password, and email.
 */
public final class AuthManager {

    private static final Path DATA_PATH = Path.of("data.json");
    private static final Pattern JSON_OBJECT_PATTERN = Pattern.compile("\\{([^}]*)\\}");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("\"email\"\\s*:\\s*\"([^\"]*)\"", Pattern.CASE_INSENSITIVE);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("\"password\"\\s*:\\s*\"([^\"]*)\"", Pattern.CASE_INSENSITIVE);

    private static final Map<String, String> credentials = new HashMap<>();
    private static boolean initialized = false;

    private AuthManager() {
    }

    public static synchronized boolean authenticate(String email, String password) throws IOException {
        ensureLoaded();
        return credentials.containsKey(email) && credentials.get(email).equals(password);
    }

    public static synchronized void register(String email, String password) throws IOException {
        ensureLoaded();
        if (email.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException("Email and password must not be empty.");
        }
        if (credentials.containsKey(email)) {
            throw new IllegalArgumentException("This email is already registered.");
        }
        credentials.put(email, password);
        save();
    }

    private static synchronized void ensureLoaded() throws IOException {
        if (initialized) {
            return;
        }

        credentials.clear();
        if (Files.exists(DATA_PATH)) {
            String content = Files.readString(DATA_PATH).trim();
            if (!content.isEmpty()) {
                loadCredentialsFromJson(content);
            }
        } else {
            save();
        }

        initialized = true;
    }

    private static void loadCredentialsFromJson(String content) {
        Matcher objectMatcher = JSON_OBJECT_PATTERN.matcher(content);
        while (objectMatcher.find()) {
            String objectText = objectMatcher.group(1);
            String email = extractField(objectText, EMAIL_PATTERN);
            String password = extractField(objectText, PASSWORD_PATTERN);
            if (email != null && password != null) {
                credentials.put(email, password);
            }
        }
    }

    private static String extractField(String source, Pattern pattern) {
        Matcher matcher = pattern.matcher(source);
        if (matcher.find()) {
            return unescapeJson(matcher.group(1));
        }
        return null;
    }

    private static void save() throws IOException {
        List<String> users = new ArrayList<>();
        for (Map.Entry<String, String> entry : credentials.entrySet()) {
            users.add(buildUserJson(entry.getKey(), entry.getValue()));
        }

        String json = "[\n" + String.join(",\n", users) + "\n]";
        Files.writeString(DATA_PATH, json, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private static String buildUserJson(String email, String password) {
        String username = deriveUsernameFromEmail(email);
        return "  {\n"
                + "    \"username\": " + toJsonString(username) + ",\n"
                + "    \"password\": " + toJsonString(password) + ",\n"
                + "    \"email\": " + toJsonString(email) + "\n"
                + "  }";
    }

    private static String deriveUsernameFromEmail(String email) {
        int atIndex = email.indexOf('@');
        return atIndex > 0 ? email.substring(0, atIndex) : email;
    }

    private static String toJsonString(String text) {
        return "\"" + text.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
    }

    private static String unescapeJson(String raw) {
        return raw.replace("\\\"", "\"").replace("\\\\", "\\");
    }
}
