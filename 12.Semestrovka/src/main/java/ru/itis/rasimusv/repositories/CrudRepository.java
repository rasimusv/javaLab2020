package ru.itis.rasimusv.repositories;

import java.util.List;

public interface CrudRepository<T, ID> {
    void save(T entity);

    void delete(ID id);

    void update(T entity);

    List<T> findById(ID id);

    List<T> findAll();

    List<T> findAll(int page, int size);
}
