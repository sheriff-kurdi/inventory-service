package com.kurdi.inventoryservice.controllers;

import com.kurdi.inventoryservice.entities.Category;
import com.kurdi.inventoryservice.repositories.CategoriesRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/categories/")
public class CategoriesController {
    @Autowired
    CategoriesRepository categoriesRepository;

    @GetMapping
    public List<Category> getAll()
    {
        return  categoriesRepository.findAll();
    }

    @PostMapping
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Category> add(@RequestBody Category category)
    {

        return new ResponseEntity<>(categoriesRepository.save(category), HttpStatus.CREATED);
    }

    @PutMapping("{name}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Category> update(@PathVariable String name)
    {
        return new ResponseEntity<>(categoriesRepository.save(Category.builder().name(name).build()), HttpStatus.OK);
    }

    @DeleteMapping("{name}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Category> delete(@PathVariable String name)
    {
        Category category = Category.builder().name(name).build();
        categoriesRepository.delete(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }


}
