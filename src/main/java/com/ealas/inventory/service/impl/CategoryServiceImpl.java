package com.ealas.inventory.service.impl;

import com.ealas.inventory.dao.ICategoryDao;
import com.ealas.inventory.model.Category;
import com.ealas.inventory.response.CategoryResponseRest;
import com.ealas.inventory.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {

        CategoryResponseRest response = new CategoryResponseRest();
        try {
            List<Category> categories = (List<Category>) categoryDao.findAll();

            response.getCategoryResponse().setCategories(categories);
            response.setMetadata("success", "200", "2024-11-11");
        } catch (Exception e){
            response.setMetadata("error", "500", "2024-11-11");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> findById(Long id) {

        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();

        try {

            Optional<Category> category = categoryDao.findById(id);

            if(category.isPresent()){
                list.add(category.get());
                response.getCategoryResponse().setCategories(list);
                response.setMetadata("success", "200", "2024-11-11");
            } else {
                response.setMetadata("error", "404", "No se encontró la categoría");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e){
            response.setMetadata("error", "-1", "Error al consultar por ID");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
