package com.Emazon.Stock.adapters.jpa.mysql.adapter;

import com.Emazon.Stock.adapters.jpa.mysql.mapper.IArticleEntityMapper;
import com.Emazon.Stock.adapters.jpa.mysql.repository.IArticleRepository;
import com.Emazon.Stock.domain.model.Article;
import com.Emazon.Stock.domain.spi.IArticlePersistencePort;
import lombok.RequiredArgsConstructor;
import com.Emazon.Stock.domain.utilities.PagedResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.List;
import com.Emazon.Stock.adapters.jpa.mysql.exception.NoDataFoundException; // Si esta excepción está definida en tu dominio
import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.ArticleEntity; // La entidad que representa los artículos en la base de datos

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

    
}
