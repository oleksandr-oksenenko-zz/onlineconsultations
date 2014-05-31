package net.onlineconsultations.web.admin.form;

import net.onlineconsultations.domain.Subject;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SubjectForm {
    @NotNull
    @Size(min = 2, max = 50, message = "Subject's name length should be between 2 and 50 characters long.")
    private String name;

    @NotNull
    private String description;

    public static SubjectForm of(Subject subject) {
        return new SubjectForm(
                subject.getName(),
                subject.getDescription());
    }

    public SubjectForm() {
    }

    public SubjectForm(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
