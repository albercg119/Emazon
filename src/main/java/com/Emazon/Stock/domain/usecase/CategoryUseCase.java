package com.Emazon.Stock.domain.usecase;

import com.Emazon.Stock.domain.api.ICategoryServicePort;
import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.utilities.PagedResult;
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

    @Override
    public List<Category> getAllCategories() {
        return categoryPersistencePort.getAllCategories();
    }

    @Override
    public PagedResult<Category> getPagedCategories(Integer page, Integer size, boolean ascending) {
        return categoryPersistencePort.getPagedCategories(page, size, ascending);
    }

    private void validateCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException(CategoryUseCaseConstants.CATEGORY_NULL_EXCEPTION_MESSAGE);
        }
        if (category.getNombre() == null || category.getNombre().isEmpty()) {
            throw new IllegalArgumentException(CategoryUseCaseConstants.CATEGORY_NAME_NULL_OR_EMPTY_MESSAGE);
        }
        if (category.getNombre().length() > 50) {
            throw new IllegalArgumentException(CategoryUseCaseConstants.CATEGORY_NAME_LENGTH_MESSAGE);
        }
        if (category.getDescripcion() == null || category.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException(CategoryUseCaseConstants.CATEGORY_DESCRIPTION_NULL_OR_EMPTY_MESSAGE);
        }
        if (category.getDescripcion().length() > 90) {
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
