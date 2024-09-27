package com.Emazon.Stock.domain.spi;

import com.Emazon.Stock.domain.model.Category;


public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    boolean existsByName(String name);
}
