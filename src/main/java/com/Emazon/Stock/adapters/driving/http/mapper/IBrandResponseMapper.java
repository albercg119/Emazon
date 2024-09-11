package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.driving.http.dto.response.BrandResponse;
import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.utilities.PagedResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandResponseMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "descripcion", target = "description")
    BrandResponse toBrandResponse(Brand brand);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "descripcion", target = "description")
    List<BrandResponse> toBrandResponseList(List<Brand> brands);

    PagedResult<BrandResponse> toBrandResponsePagedResult(PagedResult<Brand> pagedResult);
}

