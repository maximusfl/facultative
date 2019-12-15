package by.vsu.service;

public class UserNotExistsException extends Throwable {
    private Long id;

    public UserNotExistsException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
