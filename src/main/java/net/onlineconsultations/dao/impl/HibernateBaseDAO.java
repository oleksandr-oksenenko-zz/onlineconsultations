package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.GenericDao;
import net.onlineconsultations.dao.exception.NoSuchRecordException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public abstract class HibernateBaseDAO<T> implements GenericDao<T> {
    @PersistenceContext
    private EntityManager em;

    private final Class<T> entityClass;

    protected HibernateBaseDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        return em.createQuery(cb.createQuery(entityClass)).getResultList();
    }

    @Override
    public T getById(Long id) {
        T result = em.find(entityClass, id);
        if (result == null) {
            throw new NoSuchRecordException("Entity " + entityClass.toString() + " with id " + id + " is not found");
        }
        return result;
    }

    @Override
    public void save(T entity) {
        em.persist(entity);
    }

    @Override
    public void merge(T entity) {
        em.merge(entity);
    }
}
