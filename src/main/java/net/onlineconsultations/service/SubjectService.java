package net.onlineconsultations.service;

import net.onlineconsultations.domain.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getAll();

    Subject getById(Long id);

    void remove(Long subjectId);

    void save(Subject subject);

    void merge(Subject subject);
}
