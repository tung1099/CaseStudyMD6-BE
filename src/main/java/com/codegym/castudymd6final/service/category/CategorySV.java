package com.codegym.castudymd6final.service.category;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategorySV implements ICategorySV{
    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Object save(Object o) {
        return null;
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public Optional findById(Long id) {
        return Optional.empty();
    }
}