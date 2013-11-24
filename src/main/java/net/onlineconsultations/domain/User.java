package net.onlineconsultations.domain;

import java.util.Set;

public class User {
    private Long id;

    private String username;

    private String password;

    private UserRole role;

    private String firstName;

    private String middleName;

    private String lastName;

    private String qualification;

    private Set<SubSubject> subSubjects;

    public User(Long id, String username, String password, UserRole userRole,
            String firstName,
            String middleName, String lastName, String qualification,
            Set<SubSubject> userSubSubjects) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = userRole;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.qualification = qualification;
        this.subSubjects = userSubSubjects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Set<SubSubject> getUserSubSubjects() {
        return subSubjects;
    }

    public void setUserSubSubjects(Set<SubSubject> userSubSubjects) {
        this.subSubjects = userSubSubjects;
    }

    public UserRole getUserRole() {
        return role;
    }

    public void setUserRole(UserRole userRole) {
        this.role = userRole;
    }

}
