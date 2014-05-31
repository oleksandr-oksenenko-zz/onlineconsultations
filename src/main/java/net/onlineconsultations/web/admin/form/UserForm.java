package net.onlineconsultations.web.admin.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public abstract class UserForm {
    @Size(min = 5, max = 50, message = "Username length should be between 5 and 50 characters.")
    protected String username;

    @NotNull(message = "Password cannot be empty.")
    @Size(min = 5, message = "Password length should be greater than or equal to 5 characters.")
    protected String password;

    public UserForm() { }

    public UserForm(String username) {
        this.username = username;
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
}
