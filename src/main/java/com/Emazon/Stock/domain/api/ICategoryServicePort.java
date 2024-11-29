package com.Emazon.Stock.domain.api;

import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.utilities.PagedResult;

import java.util.List;

public interface ICategoryServicePort {
    void saveCategory(Category category);
    List<Category> getAllCategories();
    PagedResult<Category> getPagedCategories(Integer page, Integer size, boolean ascending);
}

