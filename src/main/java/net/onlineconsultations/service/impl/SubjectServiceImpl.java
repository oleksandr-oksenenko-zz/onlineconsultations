package net.onlineconsultations.service.impl;

import net.onlineconsultations.dao.SubSubjectDao;
import net.onlineconsultations.dao.SubjectDao;
import net.onlineconsultations.domain.Consultant;
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
    private SubjectDao subjectDao;

    @Inject
    private SubSubjectDao subSubjectDao;

    @Override
    @Transactional(readOnly = true)
    public List<Subject> getAll() {
        return subjectDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Subject getById(Long id) {
        return subjectDao.getById(id);
    }

    @Override
    @Transactional
    public void remove(Long subjectId) {
        Subject subject = subjectDao.getById(subjectId);
        for (SubSubject subSubject : subject.getSubSubjects()) {
            for (Consultant consultant : subSubject.getSubSubjectConsultants()) {
                consultant.getSubSubjects().remove(subSubject);
            }
        }
        subjectDao.remove(subject);
    }

    @Override
    @Transactional
    public void save(Subject subject) {
        subjectDao.save(subject);
    }

    @Override
    @Transactional
    public void merge(Subject subject) {
        subjectDao.merge(subject);
    }
}
