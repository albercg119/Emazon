package com.Emazon.Stock.adapters.jpa.mysql.adapter;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.BrandEntity;
import com.Emazon.Stock.adapters.jpa.mysql.exception.BrandAlreadyExistsException;
import com.Emazon.Stock.adapters.jpa.mysql.exception.NoDataFoundException;
import com.Emazon.Stock.adapters.jpa.mysql.mapper.IBrandEntityMapper;
import com.Emazon.Stock.adapters.jpa.mysql.repository.IBrandRepository;
import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.utilities.PagedResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    void saveBrand_ShouldSaveSuccessfully_WhenBrandDoesNotExist() {
        // Arrange
        Brand brand = new Brand(1L, "Apple", "Technology");
        BrandEntity brandEntity = new BrandEntity();

        when(brandRepository.findByNombre(brand.getNombre())).thenReturn(Optional.empty());
        when(brandEntityMapper.toEntity(brand)).thenReturn(brandEntity);

        // Act
        brandAdapter.saveBrand(brand);

        // Assert
        verify(brandRepository).save(brandEntity);
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandExists() {
        // Arrange
        Brand brand = new Brand(1L, "Apple", "Technology");
        when(brandRepository.findByNombre(brand.getNombre())).thenReturn(Optional.of(new BrandEntity()));

        // Act & Assert
        assertThrows(BrandAlreadyExistsException.class, () -> brandAdapter.saveBrand(brand));
        verify(brandRepository, never()).save(any());
    }

    @Test
    void getPagedBrands_ShouldReturnPagedResult_WhenBrandsExist() {
        // Arrange
        int page = 0;
        int size = 10;
        List<BrandEntity> brandEntities = Arrays.asList(
                new BrandEntity(), new BrandEntity()
        );
        Page<BrandEntity> brandPage = new PageImpl<>(brandEntities);
        List<Brand> brands = Arrays.asList(
                new Brand(1L, "Apple", "Tech"),
                new Brand(2L, "Samsung", "Electronics")
        );

        when(brandRepository.findAll(any(PageRequest.class))).thenReturn(brandPage);
        when(brandEntityMapper.toModelList(brandEntities)).thenReturn(brands);

        // Act
        PagedResult<Brand> result = brandAdapter.getPagedBrands(page, size, true);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        verify(brandRepository).findAll(any(PageRequest.class));
    }

    @Test
    void getPagedBrands_ShouldThrowException_WhenNoBrandsExist() {
        // Arrange
        when(brandRepository.findAll(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        // Act & Assert
        assertThrows(NoDataFoundException.class,
                () -> brandAdapter.getPagedBrands(0, 10, true));
    }

    @Test
    void getAllBrands_ShouldReturnList_WhenBrandsExist() {
        // Arrange
        List<BrandEntity> brandEntities = Arrays.asList(new BrandEntity(), new BrandEntity());
        List<Brand> expectedBrands = Arrays.asList(
                new Brand(1L, "Apple", "Tech"),
                new Brand(2L, "Samsung", "Electronics")
        );

        when(brandRepository.findAll()).thenReturn(brandEntities);
        when(brandEntityMapper.toModelList(brandEntities)).thenReturn(expectedBrands);

        // Act
        List<Brand> result = brandAdapter.getAllBrands();

        // Assert
        assertEquals(expectedBrands.size(), result.size());
        verify(brandRepository).findAll();
    }

    @Test
    void getAllBrands_ShouldThrowException_WhenNoBrandsExist() {
        // Arrange
        when(brandRepository.findAll()).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> brandAdapter.getAllBrands());
    }

    @Test
    void existsByName_ShouldReturnTrue_WhenBrandExists() {
        // Arrange
        String name = "Apple";
        when(brandRepository.findByNombre(name)).thenReturn(Optional.of(new BrandEntity()));

        // Act
        boolean result = brandAdapter.existsByName(name);

        // Assert
        assertTrue(result);
    }

    @Test
    void existsByName_ShouldReturnFalse_WhenBrandDoesNotExist() {
        // Arrange
        String name = "NonexistentBrand";
        when(brandRepository.findByNombre(name)).thenReturn(Optional.empty());

        // Act
        boolean result = brandAdapter.existsByName(name);

        // Assert
        assertFalse(result);
    }
}