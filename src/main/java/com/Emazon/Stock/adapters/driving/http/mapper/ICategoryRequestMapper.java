package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.driving.http.dto.request.AddCategoryRequest;
import com.Emazon.Stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.Emazon.Stock.adapters.utilities.CategoryMapperConstants;

@Mapper(componentModel = CategoryMapperConstants.SPRING_COMPONENT_MODEL)
public interface ICategoryRequestMapper {

    @Mapping(target = CategoryMapperConstants.MODEL_ID, ignore = true)
    @Mapping(source = CategoryMapperConstants.DTO_NAME, target = CategoryMapperConstants.MODEL_NOMBRE)
    @Mapping(source = CategoryMapperConstants.DTO_DESCRIPTION, target = CategoryMapperConstants.MODEL_DESCRIPCION)
    Category addRequestToCategory(AddCategoryRequest addCategoryRequest);
}