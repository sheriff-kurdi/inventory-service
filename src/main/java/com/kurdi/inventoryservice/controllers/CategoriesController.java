package com.kurdi.inventoryservice.controllers;

import com.kurdi.inventoryservice.entities.Category;
import com.kurdi.inventoryservice.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/Categories/")
public class CategoriesController {
    @Autowired
    CategoriesRepository categoriesRepository;

    @GetMapping
    public List<Category> getAll()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return  categoriesRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Category> add(@RequestBody Category category)
    {

        return new ResponseEntity<>(categoriesRepository.save(category), HttpStatus.CREATED);
    }

    @PutMapping("{name}")
    public ResponseEntity<Category> update(@PathVariable String name)
    {
        return new ResponseEntity<>(categoriesRepository.save(Category.builder().name(name).build()), HttpStatus.OK);
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Category> delete(@PathVariable String name)
    {
        Category category = Category.builder().name(name).build();
        categoriesRepository.delete(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }


}
