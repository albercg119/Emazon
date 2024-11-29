package com.Emazon.Stock.domain.utilities;

import java.util.List;

public interface Page<T> {
    List<T> getContent();
    int getPageNumber();
    int getPageSize();
    long getTotalElements();
    int getTotalPages();
    boolean isLast();
}