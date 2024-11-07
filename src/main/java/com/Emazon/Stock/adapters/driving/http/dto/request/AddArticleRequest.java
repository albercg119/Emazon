package com.Emazon.Stock.adapters.driving.http.dto.request;

import com.Emazon.Stock.adapters.utilities.AddArticleRequestConstants;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class AddArticleRequest {


    @NotNull(message = AddArticleRequestConstants.NAME_NOT_NULL_MESSAGE)
    @NotBlank(message = AddArticleRequestConstants.NAME_NOT_BLANK_MESSAGE)
    @Size(min = AddArticleRequestConstants.NAME_MIN_LENGTH,
            max = AddArticleRequestConstants.NAME_MAX_LENGTH,
            message = AddArticleRequestConstants.NAME_SIZE_MESSAGE)
    private String name;

    @NotNull(message = AddArticleRequestConstants.DESCRIPTION_NOT_NULL_MESSAGE)
    @NotBlank(message = AddArticleRequestConstants.DESCRIPTION_NOT_BLANK_MESSAGE)
    @Size(min = AddArticleRequestConstants.DESCRIPTION_MIN_LENGTH,
            max = AddArticleRequestConstants.DESCRIPTION_MAX_LENGTH,
            message = AddArticleRequestConstants.DESCRIPTION_SIZE_MESSAGE)
    private String description;

    @NotNull(message = AddArticleRequestConstants.STOCK_NOT_NULL_MESSAGE)
    private Integer stockQuantity;

    @NotNull(message = AddArticleRequestConstants.PRICE_NOT_NULL_MESSAGE)
    @DecimalMin(value = AddArticleRequestConstants.MIN_PRICE,
            inclusive = false,
            message = AddArticleRequestConstants.PRICE_DECIMAL_MIN_MESSAGE)
    private BigDecimal price;

    @NotNull(message = AddArticleRequestConstants.CATEGORIES_NOT_NULL_MESSAGE)
    private List<Long> categoryIds;

    @NotNull(message = AddArticleRequestConstants.BRAND_NOT_NULL_MESSAGE)
    private Long brandId;

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

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}