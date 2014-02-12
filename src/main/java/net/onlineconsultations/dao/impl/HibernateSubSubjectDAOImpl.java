package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.SubSubjectDAO;
import net.onlineconsultations.domain.SubSubject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class HibernateSubSubjectDAOImpl extends HibernateBaseDAO<SubSubject> implements SubSubjectDAO {
    @PersistenceContext
    private EntityManager em;

    public HibernateSubSubjectDAOImpl() {
        super(SubSubject.class);
    }
}
