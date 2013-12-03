package net.onlineconsultations.controller.dto;

import net.onlineconsultations.domain.User;

public class UserDTO {
    private Long id;

    private String firstname;

    private String middlename;

    private String lastname;

    private String qualification;

    public UserDTO(Long id, String firstname, String middlename,
            String lastname, String qualification) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.qualification = qualification;
    }

    public UserDTO(User user) {
        if (user != null) {
            this.id = user.getId();
            this.firstname = user.getFirstName();
            this.middlename = user.getMiddleName();
            this.lastname = user.getLastName();
            this.qualification = user.getQualification();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
