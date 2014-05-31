package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.SubjectDao;
import net.onlineconsultations.domain.Subject;
import org.springframework.stereotype.Repository;

@Repository
public class HibSubjectDaoImpl extends HibGenericDaoImpl<Subject> implements SubjectDao {
    public HibSubjectDaoImpl() {
        super(Subject.class);
    }
}
