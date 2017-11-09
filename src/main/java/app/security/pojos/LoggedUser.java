package app.security.pojos;

import lombok.Getter;

/**
 * Essential Logged User info pojo
 */
public class LoggedUser {

    @Getter
    private String id;

    @Getter
    private String fullName;

    @Getter
    private String image;

    public LoggedUser(String id, String fullName, String image) {
        this.id = id;
        this.fullName = fullName;
        this.image = image;
    }
}