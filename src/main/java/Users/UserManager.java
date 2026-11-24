package Users;

import java.util.ArrayList;

public class UserManager {
    private ArrayList<User> users = new ArrayList<>();


    public UserManager() {
        User admin = new User(UserType.ADMIN, "Admin123", "Admin123");
        users.add(admin);
    }

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean verifyPassword(User user, String password) {
        return user.getPassword().equals(password);
    }
}
