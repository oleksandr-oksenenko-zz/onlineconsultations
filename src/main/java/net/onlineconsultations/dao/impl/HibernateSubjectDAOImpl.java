package net.onlineconsultations.dao.impl;

import java.util.List;

import net.onlineconsultations.dao.SubjectDAO;
import net.onlineconsultations.domain.Subject;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateSubjectDAOImpl extends HibernateBaseDAO implements
	SubjectDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Subject> getAll() {
	return getSession().createCriteria(Subject.class).list();
    }

    @Override
    public Subject getById(Long id) {
	return (Subject) getSession().get(Subject.class, id);
    }

    @Override
    public void save(Subject subject) {
	getSession().save(subject);
    }
}
