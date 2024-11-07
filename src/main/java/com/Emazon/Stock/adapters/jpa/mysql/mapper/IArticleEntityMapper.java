package com.Emazon.Stock.adapters.jpa.mysql.mapper;

import com.Emazon.Stock.domain.model.Article;
import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.ArticleEntity;
import com.Emazon.Stock.adapters.utilities.ArticleEntityMapperConstants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IArticleEntityMapper {

    @Mapping(source = ArticleEntityMapperConstants.NAME_FIELD,
            target = ArticleEntityMapperConstants.NOMBRE_FIELD)
    @Mapping(source = ArticleEntityMapperConstants.DESCRIPTION_FIELD,
            target = ArticleEntityMapperConstants.DESCRIPCION_FIELD)
    @Mapping(source = ArticleEntityMapperConstants.STOCK_QUANTITY_FIELD,
            target = ArticleEntityMapperConstants.STOCK_QUANTITY_FIELD)
    @Mapping(source = ArticleEntityMapperConstants.PRICE_FIELD,
            target = ArticleEntityMapperConstants.PRICE_FIELD)
    @Mapping(source = ArticleEntityMapperConstants.BRAND_FIELD,
            target = ArticleEntityMapperConstants.BRAND_FIELD)
    @Mapping(source = ArticleEntityMapperConstants.CATEGORIES_FIELD,
            target = ArticleEntityMapperConstants.CATEGORIES_FIELD)
    ArticleEntity toArticleEntity(Article article);

    @Mapping(source = ArticleEntityMapperConstants.NOMBRE_FIELD,
            target = ArticleEntityMapperConstants.NAME_FIELD)
    @Mapping(source = ArticleEntityMapperConstants.DESCRIPCION_FIELD,
            target = ArticleEntityMapperConstants.DESCRIPTION_FIELD)
    @Mapping(source = ArticleEntityMapperConstants.STOCK_QUANTITY_FIELD,
            target = ArticleEntityMapperConstants.STOCK_QUANTITY_FIELD)
    @Mapping(source = ArticleEntityMapperConstants.PRICE_FIELD,
            target = ArticleEntityMapperConstants.PRICE_FIELD)
    @Mapping(source = ArticleEntityMapperConstants.BRAND_FIELD,
            target = ArticleEntityMapperConstants.BRAND_FIELD)
    @Mapping(source = ArticleEntityMapperConstants.CATEGORIES_FIELD,
            target = ArticleEntityMapperConstants.CATEGORIES_FIELD)
    Article toArticle(ArticleEntity articleEntity);

    List<Article> toModelList(List<ArticleEntity> articleEntities);
}