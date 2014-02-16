package net.onlineconsultations.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "sub_subject")
public class SubSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "description")
    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_subject_id")
    @NotNull
    private Subject parentSubject;

    @ManyToMany(mappedBy = "subSubjects", fetch = FetchType.EAGER)
    private Set<User> subSubjectUsers;

    public SubSubject() { }

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
