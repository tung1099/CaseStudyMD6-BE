package com.codegym.castudymd6final.controller;


import com.codegym.castudymd6final.model.entity.Category;
import com.codegym.castudymd6final.service.category.ICategorySV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("categories")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private ICategorySV categoryService;

    @GetMapping("/list")
    public ResponseEntity<List<Category>> showAllCategory(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
}
