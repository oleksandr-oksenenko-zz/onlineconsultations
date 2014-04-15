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

    @Transactional
    @Override
    public void remove(Long id) {
        Subject subject = subjectDAO.getById(id);
        for (SubSubject subSubject : subject.getSubSubjects()) {
            subSubject.getSubSubjectUsers().clear();
        }
        subjectDAO.remove(subject);
    }
}
