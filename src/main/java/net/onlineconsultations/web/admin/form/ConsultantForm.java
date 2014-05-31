package net.onlineconsultations.web.admin.form;

import net.onlineconsultations.domain.Consultant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ConsultantForm extends UserForm {
    @NotNull(message = "First name cannot be empty.")
    @Size(min = 1, max = 50, message = "First name length should be between 2 and 50 characters.")
    private String firstName;

    @Size(max = 50, message = "Middle name length should be less or equal to 50 characters.")
    private String middleName;

    @NotNull(message = "Last name cannot be empty.")
    @Size(min = 1, max = 50, message = "Last name length should be between 2 and 50 characters.")
    private String lastName;

    private String qualification;

    public static ConsultantForm of(Consultant consultant) {
        return new ConsultantForm(
                consultant.getUsername(),
                consultant.getFirstName(),
                consultant.getMiddleName(),
                consultant.getLastName(),
                consultant.getQualification()
        );
    }

    public ConsultantForm() { }

    public ConsultantForm(String username,
                          String firstName, String middleName,
                          String lastName, String qualification) {
        super(username);
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.qualification = qualification;
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
}
