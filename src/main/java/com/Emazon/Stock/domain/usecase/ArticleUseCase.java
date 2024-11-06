package com.Emazon.Stock.domain.usecase;

import com.Emazon.Stock.domain.utilities.constants.ArticleUseCaseConstants;
import com.Emazon.Stock.domain.utilities.Exceptions.ArticleAlreadyExistsDomainException;
import com.Emazon.Stock.domain.api.IArticleServicePort;
import com.Emazon.Stock.domain.model.Article;
import com.Emazon.Stock.domain.model.Category;
import com.Emazon.Stock.domain.spi.IArticlePersistencePort;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ArticleUseCase implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
    }

    @Override
    public void saveArticle(Article article) {
        if (article.getName().isEmpty() || article.getDescription().isEmpty()
                || article.getPrice() <= ArticleUseCaseConstants.MIN_PRICE
                || article.getStockQuantity() <= ArticleUseCaseConstants.MIN_STOCK) {
            throw new IllegalArgumentException(ArticleUseCaseConstants.EMPTY_FIELDS_MESSAGE);
        }

        if (article.getCategories().isEmpty()) {
            throw new IllegalArgumentException(ArticleUseCaseConstants.CATEGORY_COUNT_MESSAGE);
        }

        checkIfNameIsUnique(article.getName());

        List<Long> categoryIds = article.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toList());

        if (Boolean.TRUE.equals(validCategoryRepeat(categoryIds))) {
            throw new IllegalArgumentException(ArticleUseCaseConstants.DUPLICATE_CATEGORIES_MESSAGE);
        }

        if (categoryIds.size() > ArticleUseCaseConstants.MAX_CATEGORIES ||
                categoryIds.size() < ArticleUseCaseConstants.MIN_CATEGORIES) {
            throw new IllegalArgumentException(ArticleUseCaseConstants.CATEGORY_COUNT_MESSAGE);
        }

        this.articlePersistencePort.saveArticle(article);
    }

    private void checkIfNameIsUnique(String name) {
        boolean exists = articlePersistencePort.existsByName(name);
        if (exists) {
            throw new ArticleAlreadyExistsDomainException(ArticleUseCaseConstants.ARTICLE_NAME_UNIQUE_MESSAGE);
        }
    }

    private Boolean validCategoryRepeat(List<Long> categoryIds) {
        Set<Long> categoryIdSet = new HashSet<>(categoryIds);
        return categoryIdSet.size() != categoryIds.size();
    }
}