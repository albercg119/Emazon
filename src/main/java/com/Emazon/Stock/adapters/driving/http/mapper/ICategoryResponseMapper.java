package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.driving.http.dto.response.CategoryResponse;
import com.Emazon.Stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.Emazon.Stock.domain.utilities.PagedResult;
import com.Emazon.Stock.adapters.utilities.CategoryMapperConstants;

import java.util.List;

@Mapper(componentModel = CategoryMapperConstants.SPRING_COMPONENT_MODEL)
public interface ICategoryResponseMapper {
    @Mapping(source = CategoryMapperConstants.MODEL_ID, target = CategoryMapperConstants.DTO_ID)
    @Mapping(source = CategoryMapperConstants.MODEL_NOMBRE, target = CategoryMapperConstants.DTO_NAME)
    @Mapping(source = CategoryMapperConstants.MODEL_DESCRIPCION, target = CategoryMapperConstants.DTO_DESCRIPTION)
    CategoryResponse toCategoryResponse(Category category);

    @Mapping(source = CategoryMapperConstants.MODEL_ID, target = CategoryMapperConstants.DTO_ID)
    @Mapping(source = CategoryMapperConstants.MODEL_NOMBRE, target = CategoryMapperConstants.DTO_NAME)
    @Mapping(source = CategoryMapperConstants.MODEL_DESCRIPCION, target = CategoryMapperConstants.DTO_DESCRIPTION)
    List<CategoryResponse> toCategoryResponseList(List<Category> categories);

    PagedResult<CategoryResponse> toCategoryResponsePagedResult(PagedResult<Category> pagedResult);
}