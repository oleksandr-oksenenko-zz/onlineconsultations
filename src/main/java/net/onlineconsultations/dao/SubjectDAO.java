package net.onlineconsultations.dao;

import java.util.List;

import net.onlineconsultations.domain.Subject;

import org.springframework.dao.DataAccessException;

public interface SubjectDAO {
    List<Subject> getAll() throws DataAccessException;

    Subject get(Long id) throws DataAccessException;

    void save(Subject subject) throws DataAccessException;
}
