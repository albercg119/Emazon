package com.Emazon.Stock.domain.spi;

import com.Emazon.Stock.domain.model.Article;
import com.Emazon.Stock.domain.utilities.PagedResult;

import java.util.List;

public interface IArticlePersistencePort {
    void saveArticle(Article article);
    boolean existsByName(String name);
    PagedResult<Article> getPagedArticles(Integer page, Integer size, String sortBy, boolean ascending);
}