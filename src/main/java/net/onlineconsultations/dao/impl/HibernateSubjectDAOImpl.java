package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.SubjectDAO;
import net.onlineconsultations.domain.Subject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class HibernateSubjectDAOImpl extends HibernateBaseDAO<Subject> implements SubjectDAO {
    @PersistenceContext
    private EntityManager em;

    public HibernateSubjectDAOImpl() {
        super(Subject.class);
    }
}
