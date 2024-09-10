package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.driving.http.dto.response.CategoryResponse;
import com.Emazon.Stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.Emazon.Stock.domain.utilities.PagedResult;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "descripcion", target = "description")
    CategoryResponse toCategoryResponse(Category category);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "descripcion", target = "description")
    List<CategoryResponse> toCategoryResponseList(List<Category> categories);

    PagedResult<CategoryResponse> toCategoryResponsePagedResult(PagedResult<Category> pagedResult);

}