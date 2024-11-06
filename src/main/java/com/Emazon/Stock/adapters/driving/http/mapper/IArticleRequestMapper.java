package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.utilities.ArticleMapperConstants;
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

    @Mapping(target = ArticleMapperConstants.CATEGORIES_TARGET,
            source = ArticleMapperConstants.CATEGORY_IDS_FIELD,
            qualifiedByName = ArticleMapperConstants.MAP_CATEGORY_IDS_METHOD)
    @Mapping(target = ArticleMapperConstants.BRAND_TARGET,
            source = ArticleMapperConstants.BRAND_ID_FIELD,
            qualifiedByName = ArticleMapperConstants.MAP_BRAND_ID_METHOD)
    Article addRequestToArticle(AddArticleRequest request,
                                @Context ICategoryServicePort categoryServicePort,
                                @Context IBrandServicePort brandServicePort);

    @Named(ArticleMapperConstants.MAP_CATEGORY_IDS_METHOD)
    default List<Category> mapCategoryIds(List<Long> categoryIds,
                                          @Context ICategoryServicePort categoryServicePort) {
        return categoryIds.stream()
                .map(categoryServicePort::getCategoryById)
                .collect(Collectors.toList());
    }

    @Named(ArticleMapperConstants.MAP_BRAND_ID_METHOD)
    default Brand mapBrandId(Long brandId,
                             @Context IBrandServicePort brandServicePort) {
        return brandServicePort.getBrandById(brandId);
    }
}
