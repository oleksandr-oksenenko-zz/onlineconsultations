package net.onlineconsultations.dao.impl;

import java.util.List;

import net.onlineconsultations.dao.SubjectDAO;
import net.onlineconsultations.domain.Subject;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateSubjectDAOImpl implements SubjectDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Subject> getAll() {
        return sessionFactory.openSession().createCriteria(Subject.class)
                .list();
    }

    @Override
    public Subject getById(Long id) {
        return (Subject) sessionFactory.openSession().get(Subject.class, id);
    }

    @Override
    public void save(Subject subject) {
        sessionFactory.openSession().save(subject);
    }

}
