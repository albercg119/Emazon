package com.Emazon.Stock.domain.utilities;

import java.util.List;

public interface Page<T> {
    List<T> getContent();   // Contenido de la página
    int getPageNumber();    // Número de página
    int getPageSize();      // Tamaño de la página
    long getTotalElements(); // Total de elementos
    int getTotalPages();    // Total de páginas
    boolean isLast();       // Si es la última página
}