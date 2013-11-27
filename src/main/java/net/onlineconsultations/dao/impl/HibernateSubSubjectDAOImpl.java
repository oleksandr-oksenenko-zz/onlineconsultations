package net.onlineconsultations.dao.impl;

import java.util.List;

import net.onlineconsultations.dao.SubSubjectDAO;
import net.onlineconsultations.domain.SubSubject;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateSubSubjectDAOImpl implements SubSubjectDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<SubSubject> getAll() {
        return sessionFactory.openSession().createCriteria(SubSubject.class)
                .list();
    }

    @Override
    public SubSubject getById(Long id) {
        return (SubSubject) sessionFactory.openSession().get(SubSubject.class,
                id);
    }

    @Override
    public void save(SubSubject subSubject) {
        sessionFactory.openSession().save(subSubject);
    }

}
