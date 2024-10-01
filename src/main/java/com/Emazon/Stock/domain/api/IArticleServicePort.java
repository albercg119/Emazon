package com.Emazon.Stock.domain.api;

import com.Emazon.Stock.domain.model.Article;
import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.utilities.PagedResult;

public interface IArticleServicePort {
    void saveArticle(Article article);
    PagedResult<Article> getPagedArticles(int page, int size, String sortBy, boolean ascending);
}