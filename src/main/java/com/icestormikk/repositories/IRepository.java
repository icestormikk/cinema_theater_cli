package com.icestormikk.repositories;

import java.util.Optional;
import java.util.Set;

public interface IRepository<T, ID> {
    Set<T> findAll();
    Optional<T> findById(int id);
    IRepository<T, ID> save(T entity) throws Exception;
    IRepository<T, ID> updateById(ID id, T newEntity) throws Exception;
    IRepository<T, ID> deleteById(ID id) throws Exception;
}
