package com.Emazon.Stock.adapters.driving.http.dto.response;

public class CategorySimpleResponse {
    private final Long id;
    private final String name;

    public CategorySimpleResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}