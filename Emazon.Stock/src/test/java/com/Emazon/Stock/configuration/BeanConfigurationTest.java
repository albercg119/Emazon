package com.Emazon.Stock.configuration;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.CategoryAdapter;
import com.Emazon.Stock.adapters.jpa.mysql.mapper.ICategoryEntityMapper;
import com.Emazon.Stock.adapters.jpa.mysql.repository.ICategoryRepository;
import com.Emazon.Stock.domain.api.ICategoryServicePort;
import com.Emazon.Stock.domain.spi.ICategoryPersistencePort;
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
    private ICategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private BeanConfiguration beanConfiguration;

    @BeforeEach
    void setUp() {
        beanConfiguration = new BeanConfiguration(categoryRepository, categoryEntityMapper);
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
}