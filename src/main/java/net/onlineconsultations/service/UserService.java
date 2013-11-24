package net.onlineconsultations.service;

import net.onlineconsultations.domain.User;

public interface UserService {
    User getById(Long id);

    User getByUsername(String username);

    boolean checkUserPassword(String username, String password);

    void save(User user);
}
