package net.onlineconsultations.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("ROLE_CONSULTANT")
public class Consultant extends User {
    @Column(name = "firstname")
    private String firstName;

    @Column(name = "middlename")
    private String middleName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "qualification")
    private String qualification;

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

    public Consultant() { }

    public Consultant(String username, String password,
                      String firstName, String middleName,
                      String lastName, String qualification) {
        super(username, password);
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.qualification = qualification;
    }

    public Double getRating() {
        if (votes == 0) {
            return 0d;
        }
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void addToRaiting(double rating) {
        double tmp = this.rating * votes;
        this.votes++;
        this.rating = (tmp + rating) / votes;
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

    public Boolean getWaitingForChat() {
        return waitingForChat;
    }

    public void setWaitingForChat(Boolean waitingForChat) {
        this.waitingForChat = waitingForChat;
    }

    public Set<SubSubject> getSubSubjects() {
        return subSubjects;
    }

    public void setSubSubjects(Set<SubSubject> subSubjects) {
        this.subSubjects = subSubjects;
    }
}
