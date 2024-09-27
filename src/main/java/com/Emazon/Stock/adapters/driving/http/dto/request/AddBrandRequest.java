package com.Emazon.Stock.adapters.driving.http.dto.request;

import com.Emazon.Stock.adapters.utilities.AddBrandRequestConstants;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddBrandRequest {

    @NotBlank(message = AddBrandRequestConstants.BRAND_NAME_REQUIRED)
    @Size(max = 50, message = AddBrandRequestConstants.BRAND_NAME_MAX_SIZE)
    private final String name;

    @NotBlank(message = AddBrandRequestConstants.BRAND_DESCRIPTION_REQUIRED)
    @Size(max = 120, message = AddBrandRequestConstants.BRAND_DESCRIPTION_MAX_SIZE)
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