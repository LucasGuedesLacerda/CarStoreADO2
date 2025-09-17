package com.example.carstore.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

    T save(T obj);

    List<T> findAll();

    Optional<T> findById(Long id);

    T update(T obj);

    boolean deleteById(Long id);

}