package net.onlineconsultations.dao;

import net.onlineconsultations.domain.User;

public interface UserDao extends GenericDao<User> {
    User findByUsername(String username);
}
