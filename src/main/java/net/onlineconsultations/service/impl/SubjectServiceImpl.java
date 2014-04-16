package net.onlineconsultations.service.impl;

import net.onlineconsultations.dao.SubSubjectDAO;
import net.onlineconsultations.dao.SubjectDAO;
import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.domain.Subject;
import net.onlineconsultations.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Inject
    private SubjectDAO subjectDAO;

    @Inject
    private SubSubjectDAO subSubjectDAO;

    @Override
    public List<Subject> getAllSubjects() {
        return subjectDAO.getAll();
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectDAO.getById(id);
    }

    @Override
    public SubSubject getSubSubjectById(Long id) {
        return subSubjectDAO.getById(id);
    }

    @Override
    public List<SubSubject> getAllSubSubjects() {
        return subSubjectDAO.getAll();
    }

    @Override
    @Transactional
    public void remove(Subject subject) {
        for (SubSubject subSubject : subject.getSubSubjects()) {
            subSubject.getSubSubjectUsers().clear();
        }
        subjectDAO.remove(subject);
    }

    @Override
    @Transactional
    public void remove(SubSubject subSubject) {
        subSubject.getSubSubjectUsers().clear();
        subSubjectDAO.remove(subSubject);
    }

    @Override
    @Transactional
    public void save(Subject subject) {
        subjectDAO.save(subject);
    }

    @Override
    @Transactional
    public void save(SubSubject subSubject) {
        subSubjectDAO.save(subSubject);
    }


    @Override
    @Transactional
    public void merge(Subject subject) {
        subjectDAO.merge(subject);
    }

    @Override
    @Transactional
    public void merge(SubSubject subSubject) {
        subSubjectDAO.merge(subSubject);
    }
}
