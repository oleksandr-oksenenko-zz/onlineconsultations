package net.onlineconsultations.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sub_subject")
public class SubSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sub_subject_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject parentSubject;

    @ManyToMany(mappedBy = "subSubjects")
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
