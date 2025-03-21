package com.icestormikk.services;

import java.util.Set;

public interface IService<T, ID> {
    Set<T> getAll();
    T getById(int id);
    IService<T, ID> updateById(ID id, T entity) throws Exception;
    IService<T, ID> deleteById(ID id) throws Exception;
}
