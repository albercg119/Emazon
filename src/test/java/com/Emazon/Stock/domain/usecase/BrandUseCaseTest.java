package com.Emazon.Stock.domain.usecase;

import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.domain.spi.IBrandPersistencePort;
import com.Emazon.Stock.domain.utilities.Exceptions.BrandAlreadyExistsDomainException;
import com.Emazon.Stock.domain.utilities.constants.BrandUseCaseConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
}