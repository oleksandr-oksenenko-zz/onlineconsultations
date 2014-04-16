package net.onlineconsultations.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_sub_subject",
            joinColumns = { @JoinColumn(name = "sub_subject_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false) })
    private Set<User> subSubjectUsers = new HashSet<>();

    public SubSubject() { }

    public SubSubject(Long id, String name, String description, Subject parentSubject) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parentSubject = parentSubject;
    }

    public SubSubject(String name, String description, Subject parentSubject,
            Set<User> subSubjectUsers) {
        this.name = name;
        this.description = description;
        this.parentSubject = parentSubject;
        this.subSubjectUsers = subSubjectUsers;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!obj.getClass().equals(getClass())) {
            return false;
        }

        SubSubject rhs = (SubSubject) obj;
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
