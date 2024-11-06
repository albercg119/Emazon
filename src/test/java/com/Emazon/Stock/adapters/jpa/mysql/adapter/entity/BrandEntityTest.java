package com.Emazon.Stock.adapters.jpa.mysql.adapter.entity;

import org.junit.jupiter.api.Test;
import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.*;

class BrandEntityTest {

    @Test
    void entity_ShouldHaveCorrectAnnotations() {
        // Verify class annotations
        assertTrue(BrandEntity.class.isAnnotationPresent(Entity.class));
        assertTrue(BrandEntity.class.isAnnotationPresent(Table.class));

        Table tableAnnotation = BrandEntity.class.getAnnotation(Table.class);
        assertEquals("brand", tableAnnotation.name());
    }

    @Test
    void id_ShouldHaveCorrectAnnotations() throws NoSuchFieldException {
        // Verify ID field annotations
        var idField = BrandEntity.class.getDeclaredField("id");
        assertTrue(idField.isAnnotationPresent(Id.class));
        assertTrue(idField.isAnnotationPresent(GeneratedValue.class));
        assertTrue(idField.isAnnotationPresent(Column.class));

        GeneratedValue generatedValue = idField.getAnnotation(GeneratedValue.class);
        assertEquals(GenerationType.IDENTITY, generatedValue.strategy());

        Column columnAnnotation = idField.getAnnotation(Column.class);
        assertEquals("id", columnAnnotation.name());
    }

    @Test
    void nombre_ShouldHaveCorrectAnnotations() throws NoSuchFieldException {
        // Verify nombre field annotations
        var nombreField = BrandEntity.class.getDeclaredField("nombre");
        assertTrue(nombreField.isAnnotationPresent(Column.class));

        Column columnAnnotation = nombreField.getAnnotation(Column.class);
        assertEquals("nombre", columnAnnotation.name());
        assertEquals(50, columnAnnotation.length());
    }

    @Test
    void descripcion_ShouldHaveCorrectAnnotations() throws NoSuchFieldException {
        // Verify descripcion field annotations
        var descripcionField = BrandEntity.class.getDeclaredField("descripcion");
        assertTrue(descripcionField.isAnnotationPresent(Column.class));

        Column columnAnnotation = descripcionField.getAnnotation(Column.class);
        assertEquals("descripcion", columnAnnotation.name());
        assertEquals(120, columnAnnotation.length());
    }

    @Test
    void gettersAndSetters_ShouldWorkCorrectly() {
        // Arrange
        BrandEntity entity = new BrandEntity();
        Long id = 1L;
        String nombre = "Test Brand";
        String descripcion = "Test Description";

        // Act
        entity.setId(id);
        entity.setNombre(nombre);
        entity.setDescripcion(descripcion);

        // Assert
        assertEquals(id, entity.getId());
        assertEquals(nombre, entity.getNombre());
        assertEquals(descripcion, entity.getDescripcion());
    }

    @Test
    void fields_ShouldAcceptNullValues() {
        // Arrange
        BrandEntity entity = new BrandEntity();

        // Assert
        assertNull(entity.getId());
        assertNull(entity.getNombre());
        assertNull(entity.getDescripcion());
    }

    @Test
    void fields_ShouldHaveCorrectTypes() throws NoSuchFieldException {
        // Verify field types
        assertEquals(Long.class, BrandEntity.class.getDeclaredField("id").getType());
        assertEquals(String.class, BrandEntity.class.getDeclaredField("nombre").getType());
        assertEquals(String.class, BrandEntity.class.getDeclaredField("descripcion").getType());
    }
}