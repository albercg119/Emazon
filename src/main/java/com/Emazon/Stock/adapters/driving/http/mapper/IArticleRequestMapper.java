package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.domain.model.Article;
import com.Emazon.Stock.adapters.driving.http.dto.request.AddArticleRequest;
import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.api.ICategoryServicePort;
import com.Emazon.Stock.domain.api.IBrandServicePort;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.Context;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IArticleRequestMapper {


    @Mapping(target = "categories", source = "categoryIds", qualifiedByName = "mapCategoryIds")
    @Mapping(target = "brand", source = "brandId", qualifiedByName = "mapBrandId")
    Article addRequestToArticle(AddArticleRequest request,
                                @Context ICategoryServicePort categoryServicePort,
                                @Context IBrandServicePort brandServicePort);

    @Named("mapCategoryIds")
    default List<Category> mapCategoryIds(List<Long> categoryIds,
                                          @Context ICategoryServicePort categoryServicePort) {
        return categoryIds.stream()
                .map(categoryServicePort::getCategoryById)
                .collect(Collectors.toList());
    }

    @Named("mapBrandId")
    default Brand mapBrandId(Long brandId,
                             @Context IBrandServicePort brandServicePort) {
        return brandServicePort.getBrandById(brandId);
    }
}