package com.ealas.inventory.dao;

import com.ealas.inventory.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryDao  extends CrudRepository<Category, Long> {
}
