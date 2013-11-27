package net.onlineconsultations.service;

import java.util.List;

import net.onlineconsultations.domain.Subject;

public interface SubjectService {

    List<Subject> getAllSubjects();

    Subject getSubjectById(Long id);
}
