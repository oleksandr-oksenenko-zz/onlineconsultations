package net.onlineconsultations.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", unique = true, nullable = true)
    private Long id;

    @Column(name = "username", length = 20, unique = true, nullable = false)
    private String username;

    @Column(name = "password", length = 32, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "middlename")
    private String middleName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "qualification")
    private String qualification;

    @ManyToMany
    @JoinTable(name = "user_sub_subjects", joinColumns = { @JoinColumn(name = "user_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "sub_subject_id", nullable = false) })
    private List<SubSubject> subSubjects;

    public User() {
    }

    public User(Long id, String username, String password, Role role,
	    String firstName, String middleName, String lastName,
	    String qualification, List<SubSubject> userSubSubjects) {
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

    public List<SubSubject> getUserSubSubjects() {
	return subSubjects;
    }

    public void setUserSubSubjects(List<SubSubject> userSubSubjects) {
	this.subSubjects = userSubSubjects;
    }

    public Role getRole() {
	return role;
    }

    public void setRole(Role userRole) {
	this.role = userRole;
    }

}
