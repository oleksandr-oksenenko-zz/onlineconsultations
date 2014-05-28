package net.onlineconsultations.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ROLE_ADMIN")
public class Administrator extends User {
    public Administrator(String username, String password) {
        super(username, password);
    }

    public Administrator() { }
}
