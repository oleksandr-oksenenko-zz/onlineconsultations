package net.onlineconsultations.dao;

import net.onlineconsultations.domain.User;

public interface UserDAO extends GenericDao<User> {
    User findByUsername(String username);
}
