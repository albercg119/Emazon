package com.Emazon.Stock.configuration;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.BrandAdapter;
import com.Emazon.Stock.adapters.jpa.mysql.adapter.CategoryAdapter;
import com.Emazon.Stock.adapters.jpa.mysql.mapper.IBrandEntityMapper;
import com.Emazon.Stock.adapters.jpa.mysql.mapper.ICategoryEntityMapper;
import com.Emazon.Stock.adapters.jpa.mysql.mapper.IArticleEntityMapper;
import com.Emazon.Stock.adapters.jpa.mysql.repository.IBrandRepository;
import com.Emazon.Stock.adapters.jpa.mysql.repository.ICategoryRepository;
import com.Emazon.Stock.adapters.jpa.mysql.repository.IArticleRepository;
import com.Emazon.Stock.domain.api.IBrandServicePort;
import com.Emazon.Stock.domain.api.ICategoryServicePort;
import com.Emazon.Stock.domain.spi.IBrandPersistencePort;
import com.Emazon.Stock.domain.spi.ICategoryPersistencePort;
import com.Emazon.Stock.domain.usecase.BrandUseCase;
import com.Emazon.Stock.domain.usecase.CategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BeanConfigurationTest {

    @Mock
    private ICategoryRepository categoryRepository;
    @Mock
    private IArticleRepository articleRepository;

    @Mock
    private IArticleEntityMapper articleEntityMapper;

    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    @Mock
    private IBrandRepository brandRepository;

    @Mock
    private IBrandEntityMapper brandEntityMapper;

    @InjectMocks
    private BeanConfiguration beanConfiguration;

    @BeforeEach
    void setUp() {
        beanConfiguration = new BeanConfiguration(
                categoryRepository, categoryEntityMapper, brandRepository, brandEntityMapper, articleRepository, articleEntityMapper);
    }

    @Test
    void testCategoryPersistencePortBean() {
        // Act: llama al método que crea el bean categoryPersistencePort
        ICategoryPersistencePort categoryPersistencePort = beanConfiguration.categoryPersistencePort();

        // Assert: verifica que se devuelve una instancia de CategoryAdapter
        assertThat(categoryPersistencePort).isInstanceOf(CategoryAdapter.class);
    }

    @Test
    void testCategoryServicePortBean() {
        // Act: llama al método que crea el bean categoryServicePort
        ICategoryServicePort categoryServicePort = beanConfiguration.categoryServicePort();

        // Assert: verifica que se devuelve una instancia de CategoryUseCase
        assertThat(categoryServicePort).isInstanceOf(CategoryUseCase.class);
    }

    @Test
    void testBrandPersistencePortBean() {
        // Act: llama al método que crea el bean brandPersistencePort
        IBrandPersistencePort brandPersistencePort = beanConfiguration.brandPersistencePort();

        // Assert: verifica que se devuelve una instancia de BrandAdapter
        assertThat(brandPersistencePort).isInstanceOf(BrandAdapter.class);
    }

    @Test
    void testBrandServicePortBean() {
        // Act: llama al método que crea el bean brandServicePort
        IBrandServicePort brandServicePort = beanConfiguration.brandServicePort();

        // Assert: verifica que se devuelve una instancia de BrandUseCase
        assertThat(brandServicePort).isInstanceOf(BrandUseCase.class);
    }
}