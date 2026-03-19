package com.suraj.apartment_rental.service;

import java.util.List;

public interface BaseService<T, ID> {
    T save(T entity);
    List<T> findAll();
    T findById(ID id);
    T update(ID id, T entity);
    void delete(ID id);
}
