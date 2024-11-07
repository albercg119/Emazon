package com.Emazon.Stock.adapters.jpa.mysql.repository;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.ArticleEntity;
import com.Emazon.Stock.adapters.utilities.ArticleRepositoryConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {
    Optional<ArticleEntity> findByNombre(String nombre);

    @EntityGraph(attributePaths = {
            ArticleRepositoryConstants.CATEGORIES_ATTRIBUTE,
            ArticleRepositoryConstants.BRAND_ATTRIBUTE
    })
    Page<ArticleEntity> findAll(Pageable pageable);
}