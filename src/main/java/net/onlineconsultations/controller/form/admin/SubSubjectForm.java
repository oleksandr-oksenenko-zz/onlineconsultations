package net.onlineconsultations.controller.form.admin;

public class SubSubjectForm {
    private Long parentSubjectId;
    private String name;
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
