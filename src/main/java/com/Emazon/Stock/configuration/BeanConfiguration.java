package com.Emazon.Stock.configuration;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.ArticleAdapter;
import com.Emazon.Stock.adapters.jpa.mysql.adapter.BrandAdapter;
import com.Emazon.Stock.adapters.jpa.mysql.adapter.CategoryAdapter;
import com.Emazon.Stock.adapters.jpa.mysql.mapper.IArticleEntityMapper;
import com.Emazon.Stock.adapters.jpa.mysql.mapper.IBrandEntityMapper;
import com.Emazon.Stock.adapters.jpa.mysql.mapper.ICategoryEntityMapper;
import com.Emazon.Stock.adapters.jpa.mysql.repository.IArticleRepository;
import com.Emazon.Stock.adapters.jpa.mysql.repository.IBrandRepository;
import com.Emazon.Stock.adapters.jpa.mysql.repository.ICategoryRepository;
import com.Emazon.Stock.domain.api.IArticleServicePort;
import com.Emazon.Stock.domain.api.ICategoryServicePort;
import com.Emazon.Stock.domain.api.IBrandServicePort;
import com.Emazon.Stock.domain.usecase.ArticleUseCase;
import com.Emazon.Stock.domain.usecase.BrandUseCase;
import com.Emazon.Stock.domain.usecase.CategoryUseCase;
import com.Emazon.Stock.domain.spi.IArticlePersistencePort;
import com.Emazon.Stock.domain.spi.IBrandPersistencePort;
import com.Emazon.Stock.domain.spi.ICategoryPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration

public class BeanConfiguration {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;
    private final IArticleRepository articleRepository;
    private final IArticleEntityMapper articleEntityMapper;


    public BeanConfiguration(
            ICategoryRepository categoryRepository,
            ICategoryEntityMapper categoryEntityMapper,
            IBrandRepository brandRepository,
            IBrandEntityMapper brandEntityMapper,
            IArticleRepository articleRepository,
            IArticleEntityMapper articleEntityMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
        this.brandRepository = brandRepository;
        this.brandEntityMapper = brandEntityMapper;
        this.articleRepository = articleRepository;
        this.articleEntityMapper = articleEntityMapper;
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }


    @Bean
    public IBrandPersistencePort brandPersistencePort() {
        return new BrandAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public IBrandServicePort brandServicePort() {
        return new BrandUseCase(brandPersistencePort());
    }

    @Bean
    public IArticlePersistencePort articlePersistencePort() {
        return new ArticleAdapter(articleRepository, articleEntityMapper);
    }

    @Bean
    public IArticleServicePort articleServicePort() {
        return new ArticleUseCase(articlePersistencePort());
    }

}