package net.onlineconsultations.service;

import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.Subject;
import net.onlineconsultations.domain.User;

import java.util.List;

public interface SubjectService {

    List<Subject> getAllSubjects();

    Subject getSubjectById(Long id);

    SubSubject getSubSubjectById(Long id);

    List<SubSubject> getAllSubSubjects();

    List<SubSubject> getSubSubjectsByUser(User user);

    List<SubSubject> getSubSubjectsBySubjectId(Long subjectId);

    void removeSubject(Long subjectId);

    void removeSubSubject(Long subSubjectId);

    void save(Subject subject);

    void save(SubSubject subSubject);

    void merge(Subject subject);

    void merge(SubSubject subSubject);
}
