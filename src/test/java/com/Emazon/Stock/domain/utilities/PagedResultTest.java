package com.Emazon.Stock.domain.utilities;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PagedResultTest {

    @Test
    public void testPagedResultConstructorAndGetters() {
        // Datos de prueba
        List<String> content = Arrays.asList("Item1", "Item2", "Item3");
        int page = 1;
        int size = 3;
        long totalElements = 10L;
        int totalPages = 4;

        // Crear instancia de PagedResult
        PagedResult<String> pagedResult = new PagedResult<>(content, page, size, totalElements, totalPages);

        // Verificar que los getters devuelven los valores correctos
        assertEquals(content, pagedResult.getContent());
        assertEquals(page, pagedResult.getPage());
        assertEquals(size, pagedResult.getSize());
        assertEquals(totalElements, pagedResult.getTotalElements());
        assertEquals(totalPages, pagedResult.getTotalPages());
    }

    @Test
    public void testPagedResultSetters() {
        // Crear instancia de PagedResult vacía
        PagedResult<String> pagedResult = new PagedResult<>(null, 0, 0, 0, 0);

        // Datos de prueba para actualizar los valores
        List<String> newContent = Arrays.asList("NewItem1", "NewItem2");
        int newPage = 2;
        int newSize = 2;
        long newTotalElements = 5L;
        int newTotalPages = 3;

        // Usar los setters para actualizar los valores
        pagedResult.setContent(newContent);
        pagedResult.setPage(newPage);
        pagedResult.setSize(newSize);
        pagedResult.setTotalElements(newTotalElements);
        pagedResult.setTotalPages(newTotalPages);

        // Verificar que los setters actualizaron correctamente los valores
        assertEquals(newContent, pagedResult.getContent());
        assertEquals(newPage, pagedResult.getPage());
        assertEquals(newSize, pagedResult.getSize());
        assertEquals(newTotalElements, pagedResult.getTotalElements());
        assertEquals(newTotalPages, pagedResult.getTotalPages());
    }
}