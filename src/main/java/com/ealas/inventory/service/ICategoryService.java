package com.ealas.inventory.service;

import com.ealas.inventory.model.Category;
import com.ealas.inventory.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {

    ResponseEntity<CategoryResponseRest> search();

    ResponseEntity<CategoryResponseRest> findById(Long id);

    ResponseEntity<CategoryResponseRest> save (Category category);

    ResponseEntity<CategoryResponseRest> update (Category category, Long id);

    ResponseEntity<CategoryResponseRest> delete (Long id);
}
