package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.driving.http.dto.response.BrandResponse;
import com.Emazon.Stock.adapters.utilities.BrandMapperConstants;
import com.Emazon.Stock.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = BrandMapperConstants.SPRING_COMPONENT_MODEL)
public interface IBrandResponseMapper {
    @Mapping(source = BrandMapperConstants.ID_FIELD, target = BrandMapperConstants.ID_FIELD)
    @Mapping(source = BrandMapperConstants.NOMBRE_FIELD, target = BrandMapperConstants.NAME_FIELD)
    @Mapping(source = BrandMapperConstants.DESCRIPCION_FIELD, target = BrandMapperConstants.DESCRIPTION_FIELD)
    BrandResponse toBrandResponse(Brand brand);

    @Mapping(source = BrandMapperConstants.ID_FIELD, target = BrandMapperConstants.ID_FIELD)
    @Mapping(source = BrandMapperConstants.NOMBRE_FIELD, target = BrandMapperConstants.NAME_FIELD)
    @Mapping(source = BrandMapperConstants.DESCRIPCION_FIELD, target = BrandMapperConstants.DESCRIPTION_FIELD)
    List<BrandResponse> toBrandResponseList(List<Brand> brands);
}