package net.onlineconsultations.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    @Lob
    private String description;

    @OneToMany(mappedBy = "parentSubject", fetch = FetchType.EAGER)
    private Set<SubSubject> subSubjects;

    public Subject() { }

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
