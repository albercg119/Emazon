package com.Emazon.Stock.adapters.jpa.mysql.adapter;

import com.Emazon.Stock.adapters.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.Emazon.Stock.adapters.jpa.mysql.mapper.ICategoryEntityMapper;
import com.Emazon.Stock.adapters.jpa.mysql.repository.ICategoryRepository;
import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.spi.ICategoryPersistencePort;


public class CategoryAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.findByNombre(category.getNombre()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.findByNombre(name).isPresent();
    }

    public CategoryAdapter(ICategoryRepository categoryRepository, ICategoryEntityMapper categoryEntityMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
    }

    @Override
    public boolean existsByNameExcludingId(String nombre, Long id) {
        return categoryRepository.existsByNombreExcludingId(nombre, id);
    }
}