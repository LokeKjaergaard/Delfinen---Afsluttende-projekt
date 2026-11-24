package Users;

public class User {
    private UserType access;
    private String username;
    private String password;

    public User(UserType userType, String username, String password) {
        setAccess(userType);
        setUsername(username);
        setPassword(password);
    }

    public UserType getAccess() {
        return access;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    private void setAccess(UserType access) {
        if (access == null) {
            throw new IllegalArgumentException(
                "Access must be set!"
            );
        }
        this.access = access;
    }

    private void setPassword(String password) {
        if (password.isEmpty()){
            throw new IllegalArgumentException(
                    "Password must be set!"
            );
        }
        if (password.length() < 8 || password.length() > 32){
            throw new IllegalArgumentException(
                    "Password must be between than 8 and 32 characters!"
            );
        }
        this.password = password;
    }

    private void setUsername(String username) {
        if (username.isEmpty()){
            throw new IllegalArgumentException(
                    "Username must be set!"
            );
        }
        if (username.length() < 6 || username.length() > 16) {
            throw new IllegalArgumentException(
                    "Username must be between 6 and 16 characters!"
            );
        }
        this.username = username;
    }
}
