package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.GenericDao;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public abstract class HibernateBaseDAO<T> implements GenericDao<T> {
    @Inject
    protected SessionFactory sessionFactory;

    private final Class<T> entityClass;

    protected HibernateBaseDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<T> getAll() {
        return (List<T>) sessionFactory.openSession()
                .createCriteria(entityClass).list();
    }

    @Override
    public T getById(Long id) {
        return (T) sessionFactory.openSession().get(entityClass, id);
    }

    @Override
    public void save(T entity) {
        sessionFactory.openSession().save(entity);
    }

    @Override
    public void merge(T entity) {
        sessionFactory.openSession().merge(entity);
    }
}
