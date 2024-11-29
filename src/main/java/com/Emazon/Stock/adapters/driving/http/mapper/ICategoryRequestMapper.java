package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.driving.http.dto.request.AddCategoryRequest;
import com.Emazon.Stock.adapters.utilities.CategoryMapperConstants;
import com.Emazon.Stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {

    @Mapping(target = CategoryMapperConstants.TARGET_ID, ignore = true)
    @Mapping(source = CategoryMapperConstants.SOURCE_NAME, target = CategoryMapperConstants.TARGET_NOMBRE)
    @Mapping(source = CategoryMapperConstants.SOURCE_DESCRIPTION, target = CategoryMapperConstants.TARGET_DESCRIPCION)
    Category addRequestToCategory(AddCategoryRequest addCategoryRequest);
}