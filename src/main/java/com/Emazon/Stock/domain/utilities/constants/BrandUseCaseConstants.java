package com.Emazon.Stock.domain.utilities.constants;

public class BrandUseCaseConstants {
    private BrandUseCaseConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String BRAND_NULL_EXCEPTION_MESSAGE = "Brand cannot be null";
    public static final String BRAND_NAME_NULL_OR_EMPTY_MESSAGE = "Brand name cannot be null or empty";
    public static final String BRAND_NAME_LENGTH_MESSAGE = "Brand name cannot exceed 50 characters";
    public static final String BRAND_DESCRIPTION_NULL_OR_EMPTY_MESSAGE = "Brand description cannot be null or empty";
    public static final String BRAND_DESCRIPTION_LENGTH_MESSAGE = "Brand description cannot exceed 120 characters";
    public static final String BRAND_ALREADY_EXISTS_MESSAGE = "Brand name must be unique";
}