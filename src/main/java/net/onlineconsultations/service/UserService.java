package net.onlineconsultations.service;

import net.onlineconsultations.domain.User;

import java.util.List;

public interface UserService {
    User getById(Long id);

    List<User> getAllUsers();

    User findByUsername(String username);

    void save(User user);

    void merge(User user);
}
