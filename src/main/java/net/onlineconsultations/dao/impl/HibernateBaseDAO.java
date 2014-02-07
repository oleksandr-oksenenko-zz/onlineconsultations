package net.onlineconsultations.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class HibernateBaseDAO {
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.openSession();
    }

    public <T> void save(T entity) {
        getSession().save(entity);
    }

    public <T> void merge(T entity) {
        getSession().merge(entity);
    }

    public <T> T getById(Long id, Class<T> entityClass) {
        return (T) getSession()
                .createCriteria(entityClass)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    public <T> List<T> getAll(Class<T> entityClass) {
        return (List<T>) getSession().createCriteria(entityClass).list();
    }
}
