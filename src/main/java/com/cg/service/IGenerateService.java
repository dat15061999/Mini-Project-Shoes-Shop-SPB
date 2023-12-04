package com.cg.service;

import java.util.List;
import java.util.Optional;

public interface IGenerateService<E,T> {
    List<E> findAll();

    Optional<E> findById(T t) ;

    void save(E e);

    void delete(T t);
}
