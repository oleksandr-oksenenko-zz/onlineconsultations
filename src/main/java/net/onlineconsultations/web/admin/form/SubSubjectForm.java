package net.onlineconsultations.web.admin.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SubSubjectForm {
    @NotNull
    @Min(1)
    private Long parentSubjectId;

    @NotNull
    @Size(min = 2, max = 50, message = "Sub subject's name length should be between 2 and 50 characters long.")
    private String name;

    @NotNull
    private String description;

    public SubSubjectForm() {
    }

    public SubSubjectForm(Long parentSubjectId, String name, String description) {
        this.parentSubjectId = parentSubjectId;
        this.name = name;
        this.description = description;
    }

    public Long getParentSubjectId() {
        return parentSubjectId;
    }

    public void setParentSubjectId(Long parentSubjectId) {
        this.parentSubjectId = parentSubjectId;
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
