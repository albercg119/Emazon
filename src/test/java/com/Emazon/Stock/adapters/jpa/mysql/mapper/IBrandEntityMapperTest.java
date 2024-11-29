package com.Emazon.Stock.adapters.jpa.mysql.mapper;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.BrandEntity;
import com.Emazon.Stock.domain.model.Brand;
import com.Emazon.Stock.adapters.jpa.mysql.mapper.IBrandEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IBrandEntityMapperTest {

    private IBrandEntityMapper brandEntityMapper;

    @BeforeEach
    void setUp() {
        // Usa la implementación generada por MapStruct
        brandEntityMapper = Mappers.getMapper(IBrandEntityMapper.class);
    }

    @Test
    void testToModel() {
        // Arrange
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(1L);
        brandEntity.setNombre("Apple");
        brandEntity.setDescripcion("Technology and devices");

        // Act
        Brand brand = brandEntityMapper.toModel(brandEntity);

        // Assert
        assertNotNull(brand);
        assertEquals(1L, brand.getId());
        assertEquals("Apple", brand.getNombre());
        assertEquals("Technology and devices", brand.getDescripcion());
    }

    @Test
    void testToEntity() {
        // Arrange
        Brand brand = new Brand(1L, "Apple", "Technology and devices");

        // Act
        BrandEntity brandEntity = brandEntityMapper.toEntity(brand);

        // Assert
        assertNotNull(brandEntity);
        assertEquals(1L, brandEntity.getId());
        assertEquals("Apple", brandEntity.getNombre());
        assertEquals("Technology and devices", brandEntity.getDescripcion());
    }

    @Test
    void testToModelList() {
        // Arrange
        BrandEntity brandEntity1 = new BrandEntity();
        brandEntity1.setId(1L);
        brandEntity1.setNombre("Apple");
        brandEntity1.setDescripcion("Technology");

        BrandEntity brandEntity2 = new BrandEntity();
        brandEntity2.setId(2L);
        brandEntity2.setNombre("Samsung");
        brandEntity2.setDescripcion("Electronics and appliances");

        List<BrandEntity> brandEntities = Arrays.asList(brandEntity1, brandEntity2);

        // Act
        List<Brand> brands = brandEntityMapper.toModelList(brandEntities);

        // Assert
        assertNotNull(brands);
        assertEquals(2, brands.size());

        Brand brand1 = brands.get(0);
        assertEquals(1L, brand1.getId());
        assertEquals("Apple", brand1.getNombre());
        assertEquals("Technology", brand1.getDescripcion());

        Brand brand2 = brands.get(1);
        assertEquals(2L, brand2.getId());
        assertEquals("Samsung", brand2.getNombre());
        assertEquals("Electronics and appliances", brand2.getDescripcion());
    }
}