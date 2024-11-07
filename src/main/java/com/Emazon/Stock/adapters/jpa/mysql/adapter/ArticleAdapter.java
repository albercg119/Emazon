package com.Emazon.Stock.adapters.jpa.mysql.adapter;

import com.Emazon.Stock.adapters.jpa.mysql.mapper.IArticleEntityMapper;
import com.Emazon.Stock.adapters.jpa.mysql.repository.IArticleRepository;
import com.Emazon.Stock.domain.model.Article;
import com.Emazon.Stock.domain.spi.IArticlePersistencePort;
import com.Emazon.Stock.domain.utilities.PagedResult;
import com.Emazon.Stock.adapters.jpa.mysql.exception.NoDataFoundException;
import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.List;

@RequiredArgsConstructor
public class ArticleAdapter implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final IArticleEntityMapper articleEntityMapper;

    @Override
    public void saveArticle(Article article) {
        articleRepository.save(articleEntityMapper.toArticleEntity(article));
    }

    @Override
    public boolean existsByName(String name) {
        return articleRepository.findByNombre(name).isPresent();
    }

    @Override
    public PagedResult<Article> getPagedArticles(Integer page, Integer size, String sortBy, boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        Page<ArticleEntity> articlePage = articleRepository.findAll(pageRequest);

        if (articlePage.isEmpty()) {
            throw new NoDataFoundException();
        }

        List<Article> articles = articleEntityMapper.toModelList(articlePage.getContent());
        return new PagedResult<>(
                articles,
                articlePage.getNumber(),
                articlePage.getSize(),
                articlePage.getTotalElements(),
                articlePage.getTotalPages());
    }
}