package com.codegym.castudymd6final.service.category;

import com.codegym.castudymd6final.model.entity.Category;
import com.codegym.castudymd6final.repository.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategorySV implements ICategorySV{
    @Autowired
    private ICategoryRepo categoryRepo;
    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public void removeById(Long id) {
        categoryRepo.deleteById(id);

    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepo.findById(id);
    }
}
