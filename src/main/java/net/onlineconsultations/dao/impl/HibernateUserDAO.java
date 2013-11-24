package net.onlineconsultations.dao.impl;

import java.util.List;

import net.onlineconsultations.dao.UserDAO;
import net.onlineconsultations.domain.User;

import org.springframework.dao.DataAccessException;

public class HibernateUserDAO implements UserDAO {

    public List<User> getAll() throws DataAccessException {
        return null;
    }

    public User getById(Long id) throws DataAccessException {
        return null;
    }

    public User getByUsername(String username) throws DataAccessException {
        return null;
    }

    public void save(User subject) throws DataAccessException {}

}
