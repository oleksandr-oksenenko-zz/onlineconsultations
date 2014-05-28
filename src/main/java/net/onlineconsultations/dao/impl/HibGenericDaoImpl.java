package net.onlineconsultations.dao.impl;

import net.onlineconsultations.dao.GenericDao;
import net.onlineconsultations.dao.exception.NoSuchRecordException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public abstract class HibGenericDaoImpl<T> implements GenericDao<T> {
    @PersistenceContext
    protected EntityManager em;

    private final Class<T> entityClass;

    protected HibGenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        return em.createQuery(cq).getResultList();
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

    @Override
    public void remove(T entity) {
        em.remove(entity);
    }
}
