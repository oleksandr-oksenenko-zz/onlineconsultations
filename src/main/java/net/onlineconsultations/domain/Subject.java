package net.onlineconsultations.domain;

import java.util.Set;

public class Subject {
    private Long id;

    private String name;

    private String description;

    private Set<SubSubject> subSubjects;

    public Subject(String name, String description, Set<SubSubject> subSubjects) {
        this.name = name;
        this.description = description;
        this.subSubjects = subSubjects;
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

    public Set<SubSubject> getSubSubjects() {
        return subSubjects;
    }

    public void setSubSubjects(Set<SubSubject> subSubjects) {
        this.subSubjects = subSubjects;
    }
}
