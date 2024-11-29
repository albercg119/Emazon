package com.Emazon.Stock.domain.usecase;

import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.spi.IBrandPersistencePort;
import com.Emazon.Stock.domain.utilities.Exceptions.BrandAlreadyExistsDomainException;
import com.Emazon.Stock.domain.utilities.PagedResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBrand_ShouldSaveBrand_WhenBrandIsValid() {
        // Arrange
        Brand validBrand = new Brand(1L, "Sony", "Electronics and entertainment");

        // Mock behavior for checking unique name
        when(brandPersistencePort.existsByName(validBrand.getNombre())).thenReturn(false);

        // Act
        assertDoesNotThrow(() -> brandUseCase.saveBrand(validBrand));

        // Assert
        verify(brandPersistencePort, times(1)).saveBrand(validBrand);
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandIsNull() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> brandUseCase.saveBrand(null));
        assertEquals("La marca no puede ser nula", exception.getMessage());
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandNameIsNull() {
        // Arrange
        Brand brandWithNullName = new Brand(1L, null, "Description");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> brandUseCase.saveBrand(brandWithNullName));
        assertEquals("El nombre de la marca no puede ser nulo o vacío", exception.getMessage());
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandNameIsEmpty() {
        // Arrange
        Brand brandWithEmptyName = new Brand(1L, "", "Description");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> brandUseCase.saveBrand(brandWithEmptyName));
        assertEquals("El nombre de la marca no puede ser nulo o vacío", exception.getMessage());
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandDescriptionIsNull() {
        // Arrange
        Brand brandWithNullDescription = new Brand(1L, "Sony", null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> brandUseCase.saveBrand(brandWithNullDescription));
        assertEquals("La descripción de la marca no puede ser nula o vacía", exception.getMessage());
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandDescriptionIsEmpty() {
        // Arrange
        Brand brandWithEmptyDescription = new Brand(1L, "Sony", "");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> brandUseCase.saveBrand(brandWithEmptyDescription));
        assertEquals("La descripción de la marca no puede ser nula o vacía", exception.getMessage());
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandNameExceedsMaxLength() {
        // Arrange
        String longName = "ThisBrandNameIsDefinitelyLongerThanFiftyCharacters!";
        Brand brandWithLongName = new Brand(1L, longName, "Valid description");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> brandUseCase.saveBrand(brandWithLongName));
        assertEquals("El nombre de la marca no puede exceder los 50 caracteres", exception.getMessage());
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandDescriptionExceedsMaxLength() {
        // Arrange
        String longDescription = "This description is definitely longer than one hundred and twenty characters. It's far too long for our current model!!!!!";
        Brand brandWithLongDescription = new Brand(1L, "Sony", longDescription);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> brandUseCase.saveBrand(brandWithLongDescription));
        assertEquals("La descripción de la marca no puede exceder los 120 caracteres", exception.getMessage());
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandNameIsNotUnique() {
        // Arrange
        Brand brandWithNonUniqueName = new Brand(null, "Sony", "Valid description");

        when(brandPersistencePort.existsByName("Sony")).thenReturn(true);

        // Act & Assert
        BrandAlreadyExistsDomainException exception = assertThrows(BrandAlreadyExistsDomainException.class,
                () -> brandUseCase.saveBrand(brandWithNonUniqueName));

        assertEquals("El nombre de la marca ya existe en el sistema", exception.getMessage());

        verify(brandPersistencePort, times(1)).existsByName("Sony");
    }

    @Test
    void getPagedBrands_ShouldReturnPagedBrands_WhenDataIsAvailable() {
        // Arrange: Create a list of brands and a page
        List<Brand> brandList = Arrays.asList(
                new Brand(1L, "Brand 1", "Description 1"),
                new Brand(2L, "Brand 2", "Description 2")
        );

        PagedResult<Brand> pagedResult = new PagedResult<>(
                brandList, 0, 10, 2L, 1
        );

        // Mock behavior
        when(brandPersistencePort.getPagedBrands(0, 10, true)).thenReturn(pagedResult);

        // Act
        PagedResult<Brand> result = brandUseCase.getPagedBrands(0, 10, true);

        // Assert
        assertEquals(2, result.getContent().size());
        assertEquals("Brand 1", result.getContent().get(0).getNombre());
        assertEquals(1, result.getTotalPages());

        // Verify method call
        verify(brandPersistencePort, times(1)).getPagedBrands(0, 10, true);
    }

    @Test
    void getAllBrands_ShouldReturnAllBrands_WhenDataIsAvailable() {
        // Arrange
        Brand brand1 = new Brand(1L, "Brand1", "Description1");
        Brand brand2 = new Brand(2L, "Brand2", "Description2");

        List<Brand> brands = Arrays.asList(brand1, brand2);

        // Mock behavior
        when(brandPersistencePort.getAllBrands()).thenReturn(brands);

        // Act
        List<Brand> result = brandUseCase.getAllBrands();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Brand1", result.get(0).getNombre());
        assertEquals("Brand2", result.get(1).getNombre());

        // Verify method call
        verify(brandPersistencePort, times(1)).getAllBrands();
    }
}