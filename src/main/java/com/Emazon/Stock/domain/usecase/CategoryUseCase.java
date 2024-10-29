package com.Emazon.Stock.domain.usecase;

import com.Emazon.Stock.domain.api.ICategoryServicePort;
import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.spi.ICategoryPersistencePort;
import com.Emazon.Stock.domain.utilities.exception.CategoryAlreadyExistsDomainException;
import com.Emazon.Stock.domain.utilities.constants.CategoryUseCaseConstants;

import java.util.List;

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
            throw new IllegalArgumentException(CategoryUseCaseConstants.CATEGORY_NULL_EXCEPTION_MESSAGE);
        }
        if (category.getNombre() == null || category.getNombre().isEmpty()) {
            throw new IllegalArgumentException(CategoryUseCaseConstants.CATEGORY_NAME_NULL_OR_EMPTY_MESSAGE);
        }
        if (category.getNombre().length() > CategoryUseCaseConstants.MAX_CATEGORY_NAME_LENGTH) {
            throw new IllegalArgumentException(CategoryUseCaseConstants.CATEGORY_NAME_LENGTH_MESSAGE);
        }
        if (!category.getNombre().matches(CategoryUseCaseConstants.CATEGORY_NAME_PATTERN)) {
            throw new IllegalArgumentException(CategoryUseCaseConstants.CATEGORY_NAME_INVALID_FORMAT_MESSAGE);
        }
        if (category.getDescripcion() == null || category.getDescripcion().isEmpty()) {
            category.setDescripcion(CategoryUseCaseConstants.DEFAULT_CATEGORY_DESCRIPTION);
        }
        if (category.getDescripcion().length() > CategoryUseCaseConstants.MAX_CATEGORY_DESCRIPTION_LENGTH) {
            throw new IllegalArgumentException(CategoryUseCaseConstants.CATEGORY_DESCRIPTION_LENGTH_MESSAGE);
        }
    }

    private void checkIfNameIsUnique(String nombre, Long id) {
        boolean exists = categoryPersistencePort.existsByName(nombre);

        if (exists) {
            throw new CategoryAlreadyExistsDomainException(CategoryUseCaseConstants.CATEGORY_ALREADY_EXISTS_MESSAGE);
        }
    }
}