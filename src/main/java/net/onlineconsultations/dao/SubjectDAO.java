package net.onlineconsultations.dao;

import java.util.List;

import net.onlineconsultations.domain.Subject;

public interface SubjectDAO {
    List<Subject> getAll();

    Subject getById(Long id);

    void save(Subject subject);
}
