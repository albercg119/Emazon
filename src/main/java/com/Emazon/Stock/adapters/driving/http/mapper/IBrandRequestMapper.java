package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.Emazon.Stock.adapters.utilities.BrandMapperConstants;
import com.Emazon.Stock.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = BrandMapperConstants.SPRING_COMPONENT_MODEL)
public interface IBrandRequestMapper {

    @Mapping(target = BrandMapperConstants.ID_FIELD, ignore = BrandMapperConstants.IGNORE_ID)
    @Mapping(source = BrandMapperConstants.NAME_FIELD, target = BrandMapperConstants.NOMBRE_FIELD)
    @Mapping(source = BrandMapperConstants.DESCRIPTION_FIELD, target = BrandMapperConstants.DESCRIPCION_FIELD)
    Brand addRequestToBrand(AddBrandRequest addBrandRequest);
}