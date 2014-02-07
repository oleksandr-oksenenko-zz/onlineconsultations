package net.onlineconsultations.service;

import net.onlineconsultations.domain.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> getAllSubjects();

    Subject getSubjectById(Long id);
}
