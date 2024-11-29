package com.Emazon.Stock.adapters.jpa.mysql.adapter;

import com.Emazon.Stock.adapters.utilities.CategoryAdapterConstants;
import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.CategoryEntity;
import com.Emazon.Stock.adapters.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.Emazon.Stock.adapters.jpa.mysql.exception.NoDataFoundException;
import com.Emazon.Stock.adapters.jpa.mysql.mapper.ICategoryEntityMapper;
import com.Emazon.Stock.adapters.jpa.mysql.repository.ICategoryRepository;
import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.spi.ICategoryPersistencePort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import com.Emazon.Stock.domain.utilities.PagedResult;
import org.springframework.data.domain.Page;

import java.util.List;


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
    public PagedResult<Category> getPagedCategories(Integer page, Integer size, boolean ascending) {
        Sort sort = ascending ? Sort.by(CategoryAdapterConstants.NAME_FIELD).ascending()
                : Sort.by(CategoryAdapterConstants.NAME_FIELD).descending();
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageRequest);

        if (categoryPage.isEmpty()) {
            throw new NoDataFoundException();
        }

        List<Category> categories = categoryEntityMapper.toModelList(categoryPage.getContent());
        return new PagedResult<>(categories, categoryPage.getNumber(), categoryPage.getSize(),
                categoryPage.getTotalElements(), categoryPage.getTotalPages());
    }

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            throw new NoDataFoundException();
        }

        return categoryEntityMapper.toModelList(categories);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.findByNombre(name).isPresent();
    }

    public CategoryAdapter(ICategoryRepository categoryRepository, ICategoryEntityMapper categoryEntityMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
    }
}