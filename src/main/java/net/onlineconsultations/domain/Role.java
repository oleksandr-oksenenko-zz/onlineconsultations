package net.onlineconsultations.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;

@Entity
@Table(name = "role")
public class Role {
    public static final Role ROLE_ADMIN = new Role("ROLE_ADMIN");
    public static final Role ROLE_CONSULTANT = new Role("ROLE_CONSULTANT");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role", nullable = false, unique = true)
    private String role;

    public Role() {
    }

    public Role(String role) {
	this.role = role;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null || !(obj instanceof Role)) {
	    return false;
	}
	if (obj == this) {
	    return true;
	}
	Role rhs = (Role) obj;
	return new EqualsBuilder().append(role, rhs.getRole()).isEquals();
    }
}