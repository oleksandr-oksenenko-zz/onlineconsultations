package net.onlineconsultations.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true)
    @NotNull
    @Size(max = 50)
    private String username;

    @Column(name = "password")
    @Size(max = 64)
    @NotNull
    private String password;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "middlename")
    private String middleName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "qualification")
    private String qualification;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @NotNull
    private UserRole role;

    @Column(name = "is_waiting_for_chat", columnDefinition = "tinyint")
    private Boolean waitingForChat = false;

    @Column(name = "rating")
    private Double rating = 0d;

    @Column(name = "votes")
    private Long votes = 0L;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_sub_subject",
            joinColumns = { @JoinColumn(name = "user_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "sub_subject_id", nullable = false) })
    private Set<SubSubject> subSubjects = new HashSet<>();

    public User() { }

    public User(String username, String password, String firstName, String middleName, String lastName, String qualification, UserRole role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.qualification = qualification;
        this.role = role;
    }

    public User(Long id, String username, String password, UserRole role,
                String firstName, String middleName, String lastName,
                String qualification, Set<SubSubject> userSubSubjects) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.qualification = qualification;
        this.subSubjects = userSubSubjects;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof User)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        User rhs = (User) obj;
        return new EqualsBuilder().append(id, rhs.getId())
                .append(username, rhs.getUsername())
                .append(password, rhs.getPassword())
                .append(firstName, rhs.getFirstName())
                .append(middleName, rhs.getMiddleName())
                .append(lastName, rhs.getLastName())
                .append(role, rhs.getRole()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(username)
                .append(password)
                .append(firstName)
                .append(middleName)
                .append(lastName)
                .append(role)
                .toHashCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Set<SubSubject> getUserSubSubjects() {
        return subSubjects;
    }

    public void setUserSubSubjects(Set<SubSubject> userSubSubjects) {
        this.subSubjects = userSubSubjects;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole userRole) {
        this.role = userRole;
    }

    public Boolean isWaitingForChat() {
        return waitingForChat;
    }

    public void setWaitingForChat(Boolean waitingForChat) {
        this.waitingForChat = waitingForChat;
    }

    public Double getRating() {
        if (votes == 0) {
            return 0d;
        }
        return rating / votes;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void addToRaiting(Double rating) {
        this.votes++;
        this.rating += rating;
    }
}
