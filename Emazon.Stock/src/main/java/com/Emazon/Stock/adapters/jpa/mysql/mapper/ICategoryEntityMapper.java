package com.Emazon.Stock.adapters.jpa.mysql.mapper;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.CategoryEntity;
import com.Emazon.Stock.domain.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {
    Category toModel(CategoryEntity categoryEntity);
    CategoryEntity toEntity(Category category);
    List<Category> toModelList(List<CategoryEntity> categoryEntities);
}