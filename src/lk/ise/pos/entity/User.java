package lk.ise.pos.entity;

public class User {
    private String username;
    private String password;

    // ctrl + alt + shift + t

    // Constructor
    public User(){}
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    // Set Method
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    // Get Methods
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
