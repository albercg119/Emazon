package com.Emazon.Stock.adapters.driving.http.mapper;

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

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "brand.nombre", target = "brandName")
    @Mapping(source = "categories", target = "categories", qualifiedByName = "mapCategories")
    ArticleResponse toArticleResponse(Article article);


    List<ArticleResponse> toArticleResponseList(List<Article> articles);

    @Named("mapCategories")
    default List<CategoryResponse> mapCategories(List<Category> categories) {
        return categories.stream()
                .map(category -> new CategoryResponse(category.getId(), category.getNombre(), null))
                .collect(Collectors.toList());
    }


    default PagedResult<ArticleResponse> toArticleResponsePagedResult(PagedResult<Article> pagedResult) {
        List<ArticleResponse> articleResponses = toArticleResponseList(pagedResult.getContent());
        return new PagedResult<>(articleResponses, pagedResult.getPage(), pagedResult.getSize(),
                pagedResult.getTotalElements(), pagedResult.getTotalPages());
    }
}