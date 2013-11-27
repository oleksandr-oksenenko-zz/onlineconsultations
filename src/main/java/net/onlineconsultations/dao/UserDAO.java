package net.onlineconsultations.dao;

import java.util.List;

import net.onlineconsultations.domain.User;

public interface UserDAO {
    List<User> getAll();

    User getById(Long id);

    User getByUsername(String username);

    void save(User user);
}
