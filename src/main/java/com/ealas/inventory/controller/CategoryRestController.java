package com.ealas.inventory.controller;

import com.ealas.inventory.model.Category;
import com.ealas.inventory.response.CategoryResponseRest;
import com.ealas.inventory.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> save(@RequestBody Category category) {
        ResponseEntity<CategoryResponseRest> response = categoryService.save(category);
        return response;
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> update(@RequestBody Category category, @PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = categoryService.update(category, id);
        return response;
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> delete(@PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = categoryService.delete(id);
        return response;
    }
}
