package net.onlineconsultations.service;

import net.onlineconsultations.domain.User;

public interface UserService {
    User getById(Long id);

    User findByUsername(String username);

    void save(User user);
}
