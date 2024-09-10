package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.driving.http.dto.request.AddCategoryRequest;
import com.Emazon.Stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "description", target = "descripcion")
    Category addRequestToCategory(AddCategoryRequest addCategoryRequest);


}