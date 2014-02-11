package net.onlineconsultations.controller.page.model;

import javax.validation.constraints.NotNull;

public class UserLoginInfo {
    @NotNull
    private String username;

    @NotNull
    private String password;

    public UserLoginInfo() { }

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
