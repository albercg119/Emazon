package com.Emazon.Stock.domain.spi;

import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.utilities.PagedResult;

import java.util.List;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    PagedResult<Category> getPagedCategories(Integer page, Integer size, boolean ascending);
    List<Category> getAllCategories();
    boolean existsByName(String name);
    Category getCategoryById(Long id);
}