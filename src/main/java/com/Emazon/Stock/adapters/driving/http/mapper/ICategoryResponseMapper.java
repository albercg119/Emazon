package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.driving.http.dto.response.CategoryResponse;
import com.Emazon.Stock.adapters.utilities.CategoryMapperConstants;
import com.Emazon.Stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {
    @Mapping(source = CategoryMapperConstants.SOURCE_ID, target = CategoryMapperConstants.TARGET_ID)
    @Mapping(source = CategoryMapperConstants.TARGET_NOMBRE, target = CategoryMapperConstants.SOURCE_NAME)
    @Mapping(source = CategoryMapperConstants.TARGET_DESCRIPCION, target = CategoryMapperConstants.SOURCE_DESCRIPTION)
    CategoryResponse toCategoryResponse(Category category);

    @Mapping(source = CategoryMapperConstants.SOURCE_ID, target = CategoryMapperConstants.TARGET_ID)
    @Mapping(source = CategoryMapperConstants.TARGET_NOMBRE, target = CategoryMapperConstants.SOURCE_NAME)
    @Mapping(source = CategoryMapperConstants.TARGET_DESCRIPCION, target = CategoryMapperConstants.SOURCE_DESCRIPTION)
    List<CategoryResponse> toCategoryResponseList(List<Category> categories);
}