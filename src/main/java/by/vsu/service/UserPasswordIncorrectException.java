package by.vsu.service;

public class UserPasswordIncorrectException extends Throwable {
    private Long id;

    public UserPasswordIncorrectException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
