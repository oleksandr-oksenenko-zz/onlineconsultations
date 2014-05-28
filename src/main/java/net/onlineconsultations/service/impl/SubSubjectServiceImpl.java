package net.onlineconsultations.service.impl;

import net.onlineconsultations.dao.SubSubjectDao;
import net.onlineconsultations.domain.Consultant;
import net.onlineconsultations.domain.SubSubject;
import net.onlineconsultations.service.SubSubjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class SubSubjectServiceImpl implements SubSubjectService {
    @Inject
    private SubSubjectDao subSubjectDao;

    @Override
    @Transactional(readOnly = true)
    public SubSubject getById(Long id) {
        return subSubjectDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubSubject> getAll() {
        return subSubjectDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubSubject> getByConsultant(Consultant consultant) {
        return subSubjectDao.getByConsultant(consultant);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubSubject> getBySubjectId(Long subjectId) {
        return subSubjectDao.getBySubjectId(subjectId);
    }

    @Override
    @Transactional
    public void remove(Long subSubjectId) {
        SubSubject subSubject = subSubjectDao.getById(subSubjectId);
        subSubject.getSubSubjectConsultants().clear();
        subSubjectDao.remove(subSubject);
    }

    @Override
    @Transactional
    public void save(SubSubject subSubject) {
        subSubjectDao.save(subSubject);
    }

    @Override
    @Transactional
    public void merge(SubSubject subSubject) {
        subSubjectDao.merge(subSubject);
    }
}
