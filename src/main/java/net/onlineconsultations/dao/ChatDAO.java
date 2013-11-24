package net.onlineconsultations.dao;

import java.util.List;

import net.onlineconsultations.domain.Chat;

import org.springframework.dao.DataAccessException;

public interface ChatDAO {
    List<Chat> getAll() throws DataAccessException;

    Chat get(Long id) throws DataAccessException;

    void save(Chat subject) throws DataAccessException;
}
