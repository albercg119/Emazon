package com.Emazon.Stock.adapters.jpa.mysql.adapter;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.BrandEntity;
import com.Emazon.Stock.adapters.jpa.mysql.exception.BrandAlreadyExistsException;
import com.Emazon.Stock.adapters.jpa.mysql.mapper.IBrandEntityMapper;
import com.Emazon.Stock.adapters.jpa.mysql.repository.IBrandRepository;
import com.Emazon.Stock.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandAdapterTest {

    @Mock
    private IBrandRepository brandRepository;

    @Mock
    private IBrandEntityMapper brandEntityMapper;

    @InjectMocks
    private BrandAdapter brandAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveBrand_WhenBrandDoesNotExist_ShouldSaveSuccessfully() {
        // Arrange
        Brand brand = new Brand(1L, "Apple", "Technology and devices");
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(1L);
        brandEntity.setNombre("Apple");
        brandEntity.setDescripcion("Technology and devices");

        when(brandRepository.findByNombre(brand.getNombre())).thenReturn(Optional.empty());
        when(brandEntityMapper.toEntity(brand)).thenReturn(brandEntity);

        // Act
        brandAdapter.saveBrand(brand);

        // Assert
        verify(brandRepository, times(1)).save(brandEntity);
    }

    @Test
    void testSaveBrand_WhenBrandExists_ShouldThrowBrandAlreadyExistsException() {
        // Arrange
        Brand brand = new Brand(1L, "Apple", "Technology and devices");
        when(brandRepository.findByNombre(brand.getNombre())).thenReturn(Optional.of(new BrandEntity()));

        // Act & Assert
        assertThrows(BrandAlreadyExistsException.class, () -> {
            brandAdapter.saveBrand(brand);
        });

        verify(brandRepository, never()).save(any(BrandEntity.class));
    }

    @Test
    void testExistsByName_WhenBrandExists_ShouldReturnTrue() {
        // Arrange
        String brandName = "Apple";
        when(brandRepository.findByNombre(brandName)).thenReturn(Optional.of(new BrandEntity()));

        // Act
        boolean result = brandAdapter.existsByName(brandName);

        // Assert
        assertTrue(result);
        verify(brandRepository, times(1)).findByNombre(brandName);
    }

    @Test
    void testExistsByName_WhenBrandDoesNotExist_ShouldReturnFalse() {
        // Arrange
        String brandName = "Apple";
        when(brandRepository.findByNombre(brandName)).thenReturn(Optional.empty());

        // Act
        boolean result = brandAdapter.existsByName(brandName);

        // Assert
        assertFalse(result);
        verify(brandRepository, times(1)).findByNombre(brandName);
    }

}