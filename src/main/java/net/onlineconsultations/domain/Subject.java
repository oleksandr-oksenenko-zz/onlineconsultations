package net.onlineconsultations.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.HashSet;
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

    @OneToMany(mappedBy = "parentSubject", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<SubSubject> subSubjects = new HashSet<>();

    public Subject() { }

    public Subject(String name, String description, Set<SubSubject> subSubjects) {
        this.name = name;
        this.description = description;
        this.subSubjects = subSubjects;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!obj.getClass().equals(getClass())) {
            return false;
        }

        Subject rhs = (Subject) obj;
        return new EqualsBuilder()
                .append(id, rhs.id)
                .append(name, rhs.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(name)
                .toHashCode();
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
