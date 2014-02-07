package net.onlineconsultations.service.impl;

import net.onlineconsultations.dao.SubjectDAO;
import net.onlineconsultations.domain.Subject;
import net.onlineconsultations.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectDAO subjectDAO;

    @Override
    public List<Subject> getAllSubjects() {
        return subjectDAO.getAll();
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectDAO.getById(id);
    }
}
