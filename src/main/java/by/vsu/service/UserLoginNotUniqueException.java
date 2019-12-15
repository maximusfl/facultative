package by.vsu.service;

public class UserLoginNotUniqueException extends Throwable {
    private String login;

    public UserLoginNotUniqueException(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
