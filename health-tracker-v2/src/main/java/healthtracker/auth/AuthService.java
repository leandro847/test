package healthtracker.auth;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class AuthService {

    private static final String USER_FILE = "/data/users.json";

    public static List<User> loadUsers() {
        try {
            InputStream is = AuthService.class.getResourceAsStream(USER_FILE);

            if (is == null) {
                throw new RuntimeException("users.json not found on classpath: " + USER_FILE);
            }

            Gson gson = new Gson();
            Type userListType = new TypeToken<List<User>>() {}.getType();

            return gson.fromJson(new InputStreamReader(is), userListType);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load users", e);
        }
    }

    public static User login(String username, String password) {
        for (User user : loadUsers()) {
            if (user.getUsername().equals(username)
                && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static boolean userExists(String username) {
        return loadUsers()
                .stream()
                .anyMatch(u -> u.getUsername().equals(username));
    }
}
