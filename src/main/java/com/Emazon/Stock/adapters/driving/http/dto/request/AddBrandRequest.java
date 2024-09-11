package com.Emazon.Stock.adapters.driving.http.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddBrandRequest {

    @NotBlank(message = "El nombre de la marca es obligatorio.")
    @Size(max = 50, message = "El nombre de la marca no puede exceder los 50 caracteres.")
    private final String name;

    @NotBlank(message = "La descripción de la marca es obligatoria.")
    @Size(max = 120, message = "La descripción no puede exceder los 120 caracteres.")
    private final String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AddBrandRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }
}