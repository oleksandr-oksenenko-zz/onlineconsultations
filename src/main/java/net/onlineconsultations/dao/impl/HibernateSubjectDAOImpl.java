package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.SubjectDAO;
import net.onlineconsultations.domain.Subject;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateSubjectDAOImpl extends HibernateBaseDAO<Subject>
        implements SubjectDAO {

    public HibernateSubjectDAOImpl() {
        super(Subject.class);
    }
}
