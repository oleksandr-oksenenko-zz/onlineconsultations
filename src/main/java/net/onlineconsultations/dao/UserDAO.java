package net.onlineconsultations.dao;

import java.util.List;

import net.onlineconsultations.domain.User;

import org.springframework.dao.DataAccessException;

public interface UserDAO {
    List<User> getAll() throws DataAccessException;

    User getById(Long id) throws DataAccessException;

    User getByUsername(String username) throws DataAccessException;

    void save(User subject) throws DataAccessException;
}
