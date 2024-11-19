package com.ealas.inventory.controller;

import com.ealas.inventory.response.CategoryResponseRest;
import com.ealas.inventory.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> viewAll() {
        ResponseEntity<CategoryResponseRest> response = categoryService.search();
        return response;
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> viewById(@PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = categoryService.findById(id);
        return response;
    }
}
