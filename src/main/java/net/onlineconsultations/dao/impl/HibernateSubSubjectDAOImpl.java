package net.onlineconsultations.dao.impl;

import java.util.List;

import net.onlineconsultations.dao.SubSubjectDAO;
import net.onlineconsultations.domain.SubSubject;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateSubSubjectDAOImpl extends HibernateBaseDAO implements
	SubSubjectDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<SubSubject> getAll() {
	return getSession().createCriteria(SubSubject.class).list();
    }

    @Override
    public SubSubject getById(Long id) {
	return (SubSubject) getSession().get(SubSubject.class, id);
    }

    @Override
    public void save(SubSubject subSubject) {
	getSession().save(subSubject);
    }
}
