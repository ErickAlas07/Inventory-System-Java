package com.ealas.inventory.response;

import com.ealas.inventory.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {

    private List<Category> categories;
}
