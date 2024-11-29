package com.Emazon.Stock.adapters.jpa.mysql.mapper;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.BrandEntity;
import com.Emazon.Stock.domain.model.Brand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {
    Brand toModel(BrandEntity brandEntity);
    BrandEntity toEntity(Brand brand);
    List<Brand> toModelList(List<BrandEntity> brandEntities);
}