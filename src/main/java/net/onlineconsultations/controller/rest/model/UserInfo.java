package net.onlineconsultations.controller.rest.model;

import net.onlineconsultations.domain.User;

public class UserInfo {
    private final String username;

    private final String firstname;

    private final String middlename;

    private final String lastname;

    private final String qualification;

    public UserInfo(String username, String firstname, String middlename,
                    String lastname, String qualification) {
        this.username = username;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.qualification = qualification;
    }

    public static UserInfo of(User user) {
        return new UserInfo(
                user.getUsername(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getQualification());
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getQualification() {
        return qualification;
    }
}