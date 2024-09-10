package com.Emazon.Stock.adapters.driving.http.dto.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class AddCategoryRequest {

    @NotBlank(message = "El nombre de la categoría es obligatorio.")
    @Size(max = 50, message = "El nombre de la categoría no puede exceder los 50 caracteres.")
    private final String name;

    @NotBlank(message = "La descripción de la categoría es obligatoria.")
    @Size(max = 90, message = "La descripción no puede exceder los 90 caracteres.")
    private final String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AddCategoryRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
