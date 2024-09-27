package com.Emazon.Stock.adapters.jpa.mysql.repository;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {
    Optional<ArticleEntity> findByNombre(String nombre);
}