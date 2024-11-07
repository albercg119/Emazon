package com.Emazon.Stock.adapters.driving.http.dto.response;

import com.Emazon.Stock.domain.model.Category;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

public class CategoryResponse {
    private final Long id;
    private final String name;
    private final String description;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public CategoryResponse(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}