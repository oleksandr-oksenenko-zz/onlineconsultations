package net.onlineconsultations.domain;

import java.util.Set;

public class SubSubject {
    private Long id;

    private String name;

    private String description;

    private Subject parentSubject;

    private Set<User> subSubjectUsers;

    public SubSubject(String name, String description, Subject parentSubject,
            Set<User> subSubjectUsers) {
        this.name = name;
        this.description = description;
        this.parentSubject = parentSubject;
        this.subSubjectUsers = subSubjectUsers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Subject getParentSubject() {
        return parentSubject;
    }

    public void setParentSubject(Subject parentSubject) {
        this.parentSubject = parentSubject;
    }

    public Set<User> getSubSubjectUsers() {
        return subSubjectUsers;
    }

    public void setSubSubjectUsers(Set<User> subSubjectUsers) {
        this.subSubjectUsers = subSubjectUsers;
    }

}
