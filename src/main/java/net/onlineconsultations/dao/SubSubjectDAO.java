package net.onlineconsultations.dao;

import java.util.List;

import net.onlineconsultations.domain.SubSubject;

import org.springframework.dao.DataAccessException;

public interface SubSubjectDAO {
    List<SubSubject> getAll() throws DataAccessException;

    SubSubject get(Long id) throws DataAccessException;

    void save(SubSubject subject) throws DataAccessException;
}
