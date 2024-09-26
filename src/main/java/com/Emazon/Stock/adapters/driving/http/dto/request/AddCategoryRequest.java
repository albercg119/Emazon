package com.Emazon.Stock.adapters.driving.http.dto.request;

import com.Emazon.Stock.adapters.utilities.AddCategoryRequestConstants;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddCategoryRequest {

    @NotBlank(message = AddCategoryRequestConstants.CATEGORY_NAME_REQUIRED)
    @Size(max = 50, message = AddCategoryRequestConstants.CATEGORY_NAME_MAX_SIZE)
    private final String name;

    @NotBlank(message = AddCategoryRequestConstants.CATEGORY_DESCRIPTION_REQUIRED)
    @Size(max = 90, message = AddCategoryRequestConstants.CATEGORY_DESCRIPTION_MAX_SIZE)
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
