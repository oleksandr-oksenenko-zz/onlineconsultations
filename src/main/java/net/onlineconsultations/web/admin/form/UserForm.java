package net.onlineconsultations.web.admin.form;

import net.onlineconsultations.domain.User;
import net.onlineconsultations.domain.UserRole;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserForm {
    @NotNull(message = "First name cannot be empty.")
    @Size(min = 1, max = 50, message = "First name length should be between 2 and 50 characters.")
    private String firstName;

    @Size(max = 50, message = "Middle name length should be less or equal to 50 characters.")
    private String middleName;

    @NotNull(message = "Last name cannot be empty.")
    @Size(min = 1, max = 50, message = "Last name length should be between 2 and 50 characters.")
    private String lastName;
    private String qualification;

    @Size(min = 5, max = 50, message = "Username length should be between 5 and 50 characters.")
    private String username;

    @NotNull(message = "Password cannot be empty.")
    @Size(min = 5, message = "Password length should be greater than or equal to 5 characters.")
    private String password;

    @NotNull(message = "User role cannot be empty.")
    private UserRole userRole;

    public static UserForm of(User user) {
        return new UserForm(
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getQualification(),
                user.getUsername(),
                "",
                user.getRole()
        );
    }

    public UserForm() {
    }

    public UserForm(String firstName, String middleName, String lastName, String qualification, String username, String password, UserRole userRole) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.qualification = qualification;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
