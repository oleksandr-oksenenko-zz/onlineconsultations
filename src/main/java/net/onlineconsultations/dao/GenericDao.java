package net.onlineconsultations.dao;

import java.util.List;

public interface GenericDao<T> {
    List<T> getAll();

    T getById(Long id);

    void save(T entity);

    void merge(T entity);
}
