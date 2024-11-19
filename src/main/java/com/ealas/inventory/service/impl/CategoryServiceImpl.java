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
        } catch (Exception e) {
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
            if (category.isPresent()) {
                list.add(category.get());
                response.getCategoryResponse().setCategories(list);
                response.setMetadata("success", "200", "2024-11-11");
            } else {
                response.setMetadata("error", "404", "No se encontró la categoría");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetadata("error", "-1", "Error al consultar por ID");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> save(Category category) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();

        try {

            Category categorySaved = categoryDao.save(category);
            if (categorySaved != null) {
                list.add(categorySaved);
                response.getCategoryResponse().setCategories(list);
                response.setMetadata("success", "200", "2024-11-18");
            } else {
                response.setMetadata("error", "400", "Categoría no guardada");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            response.setMetadata("error", "500", "Error al guardar la categoría");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();

        try{

            Optional<Category> categoryOptional = categoryDao.findById(id);
            if(categoryOptional.isPresent()){
                Category categoryUpdate = categoryOptional.get();

                categoryUpdate.setName(category.getName());
                categoryUpdate.setDescription(category.getDescription());

                Category categorySaved = categoryDao.save(categoryUpdate);

                if (categorySaved != null) {
                    list.add(categorySaved);
                    response.getCategoryResponse().setCategories(list);
                    response.setMetadata("success", "200", "2024-11-18");
                } else {
                    response.setMetadata("error", "400", "Categoría no actualizada");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                response.setMetadata("error", "404", "No se encontró la categoría");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetadata("error", "500", "Error al actualizar la categoría");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> delete(Long id) {

        CategoryResponseRest response = new CategoryResponseRest();

        try {
            Optional<Category> category = categoryDao.findById(id);
            if (category.isPresent()) {
                categoryDao.deleteById(id);
                response.setMetadata("success", "200", "2024-11-11");
            } else {
                response.setMetadata("error", "404", "No se encontró la categoría");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetadata("error", "500", "Error al eliminar categoría");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
