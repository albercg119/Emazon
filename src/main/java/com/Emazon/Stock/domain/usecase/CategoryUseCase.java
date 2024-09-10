package com.Emazon.Stock.domain.usecase;

import com.Emazon.Stock.domain.api.ICategoryServicePort;
import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.spi.ICategoryPersistencePort;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        validateCategory(category);
        checkIfNameIsUnique(category.getNombre(), category.getId());
        categoryPersistencePort.saveCategory(category);
    }

    private void validateCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (category.getNombre() == null || category.getNombre().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        if (category.getNombre().length() > 50) {
            throw new IllegalArgumentException("Category name cannot exceed 50 characters");
        }
        if (category.getDescripcion() == null || category.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("Category description cannot be null or empty");
        }
        if (category.getDescripcion().length() > 90) {
            throw new IllegalArgumentException("Category description cannot exceed 90 characters");
        }
    }

    private void checkIfNameIsUnique(String nombre, Long id) {
        boolean exists;
        if (id == null) {
            exists = categoryPersistencePort.existsByName(nombre);
        } else {
            exists = categoryPersistencePort.existsByNameExcludingId(nombre, id);
        }

        if (exists) {
            throw new IllegalArgumentException("Category name must be unique");
        }
    }
}
