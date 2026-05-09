package healthtracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author  Jincy
 */
public final class AuthManager {

    private static final Path DATA_PATH = Path.of(System.getProperty("user.dir"), "data.json");
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
            String data = Files.readString(DATA_PATH).trim();
            if (data.startsWith("{") && data.endsWith("}")) {
                data = data.substring(1, data.length() - 1).trim();
                if (!data.isEmpty()) {
                    String[] entries = data.split(",\s*(?=(?:[^"]*"[^"]*")*[^"]*$)");
                    for (String entry : entries) {
                        String[] parts = entry.split(":", 2);
                        if (parts.length == 2) {
                            String key = parseJsonString(parts[0].trim());
                            String value = parseJsonString(parts[1].trim());
                            if (key != null && value != null) {
                                credentials.put(key, value);
                            }
                        }
                    }
                }
            }
        } else {
            save();
        }
        initialized = true;
    }

    private static void save() throws IOException {
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        boolean first = true;
        for (Map.Entry<String, String> entry : credentials.entrySet()) {
            if (!first) {
                json.append(",\n");
            }
            first = false;
            json.append("  ")
                .append(toJsonString(entry.getKey()))
                .append(": ")
                .append(toJsonString(entry.getValue()));
        }
        json.append("\n}");
        Files.writeString(DATA_PATH, json.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private static String toJsonString(String text) {
        return "\"" + text.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
    }

    private static String parseJsonString(String raw) {
        raw = raw.trim();
        if (raw.startsWith("\"") && raw.endsWith("\"")) {
            String content = raw.substring(1, raw.length() - 1);
            return content.replace("\\\"", "\"").replace("\\\\", "\\");
        }
        return null;
    }
}
