package com.Emazon.Stock.adapters.jpa.mysql.mapper;

import com.Emazon.Stock.domain.model.Article;
import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IArticleEntityMapper {


    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "description", target = "descripcion")
    @Mapping(source = "stockQuantity", target = "stockQuantity")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "categories", target = "categories")
    ArticleEntity toArticleEntity(Article article);


    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "descripcion", target = "description")
    @Mapping(source = "stockQuantity", target = "stockQuantity")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "categories", target = "categories")
    Article toArticle(ArticleEntity articleEntity);


    List<Article> toModelList(List<ArticleEntity> articleEntities);
}