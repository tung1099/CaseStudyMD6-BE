package com.codegym.castudymd6final.controller;


import com.codegym.castudymd6final.model.dto.ShowCategory;
import com.codegym.castudymd6final.model.entity.Category;
import com.codegym.castudymd6final.model.entity.User;
import com.codegym.castudymd6final.service.category.ICategorySV;
import com.codegym.castudymd6final.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("categories")
@CrossOrigin("*")
public class    CategoryController {
    @Autowired
    private ICategorySV categoryService;

    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<Category>> showAllCategory() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create/{user_id}")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category, @PathVariable Long user_id) {
        User user = userService.findById(user_id).get();
        Category category1 = new Category(category.getName(), user);
        categoryService.save(category1);
        return new ResponseEntity<>(category1, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}/{user_id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long user_id, @PathVariable Long id, @RequestBody Category newCate) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        newCate.setId(id);
        User user = userService.findById(user_id).get();
        Category category1 = new Category(newCate.getName(), user);
        categoryService.save(category1);
        newCate.setId(user_id);
        return new ResponseEntity<>(categoryService.save(newCate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.removeById(id);
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/list/{user_id}")
    public ResponseEntity<Iterable<ShowCategory>> showAllCategoryByUserId(@PathVariable Long user_id) {
        Iterable<ShowCategory> showCategories = categoryService.getAllCategoryByUserId(user_id);
        return new ResponseEntity<>(showCategories, HttpStatus.OK);
    }

}
