package com.Emazon.Stock.adapters.driving.http.mapper;

import com.Emazon.Stock.adapters.utilities.ArticleMapperConstants;
import com.Emazon.Stock.domain.model.Article;
import com.Emazon.Stock.adapters.driving.http.dto.response.ArticleResponse;
import com.Emazon.Stock.adapters.driving.http.dto.response.CategoryResponse;
import com.Emazon.Stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import com.Emazon.Stock.domain.utilities.PagedResult;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface IArticleResponseMapper {

    @Mapping(source = ArticleMapperConstants.ID_FIELD,
            target = ArticleMapperConstants.ID_FIELD)
    @Mapping(source = ArticleMapperConstants.NAME_FIELD,
            target = ArticleMapperConstants.NAME_FIELD)
    @Mapping(source = ArticleMapperConstants.DESCRIPTION_FIELD,
            target = ArticleMapperConstants.DESCRIPTION_FIELD)
    @Mapping(source = ArticleMapperConstants.PRICE_FIELD,
            target = ArticleMapperConstants.PRICE_FIELD)
    @Mapping(source = ArticleMapperConstants.BRAND_NOMBRE_FIELD,
            target = ArticleMapperConstants.BRAND_NAME_TARGET)
    @Mapping(source = ArticleMapperConstants.CATEGORIES_FIELD,
            target = ArticleMapperConstants.CATEGORIES_TARGET,
            qualifiedByName = ArticleMapperConstants.MAP_CATEGORIES_METHOD)
    ArticleResponse toArticleResponse(Article article);

    List<ArticleResponse> toArticleResponseList(List<Article> articles);

    @Named(ArticleMapperConstants.MAP_CATEGORIES_METHOD)
    default List<CategoryResponse> mapCategories(List<Category> categories) {
        return categories.stream()
                .map(category -> new CategoryResponse(category.getId(),
                        category.getNombre(),
                        null))
                .collect(Collectors.toList());
    }

    default PagedResult<ArticleResponse> toArticleResponsePagedResult(PagedResult<Article> pagedResult) {
        List<ArticleResponse> articleResponses = toArticleResponseList(pagedResult.getContent());
        return new PagedResult<>(articleResponses,
                pagedResult.getPage(),
                pagedResult.getSize(),
                pagedResult.getTotalElements(),
                pagedResult.getTotalPages());
    }
}