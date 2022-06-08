package com.codegym.castudymd6final.service;

import java.util.List;
import java.util.Optional;

public interface IGeneralService <T>{
    List<T> findAll();

    T save(T t);

    void removeById(Long id);

    Optional<T> findById(Long id);
}
