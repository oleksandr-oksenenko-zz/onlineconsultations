package net.onlineconsultations.service;

import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> getAllSubjects();

    Subject getSubjectById(Long id);

    SubSubject getSubSubjectById(Long id);

    List<SubSubject> getAllSubSubjects();

    void remove(Subject subject);

    void remove(SubSubject subSubject);

    void save(Subject subject);

    void save(SubSubject subSubject);

    void merge(Subject subject);

    void merge(SubSubject subSubject);
}
