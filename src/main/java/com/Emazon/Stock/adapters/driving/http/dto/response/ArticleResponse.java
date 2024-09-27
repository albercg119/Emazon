package com.Emazon.Stock.adapters.driving.http.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String brandName;
    private List<CategoryResponse> categories;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<CategoryResponse> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryResponse> categories) {
        this.categories = categories;
    }
}