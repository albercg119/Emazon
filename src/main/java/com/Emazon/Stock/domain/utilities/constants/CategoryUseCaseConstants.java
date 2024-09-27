package com.Emazon.Stock.domain.utilities.constants;

public class CategoryUseCaseConstants {
    private CategoryUseCaseConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CATEGORY_NULL_EXCEPTION_MESSAGE = "Category cannot be null";
    public static final String CATEGORY_NAME_NULL_OR_EMPTY_MESSAGE = "Category name cannot be null or empty";
    public static final String CATEGORY_NAME_LENGTH_MESSAGE = "Category name cannot exceed 50 characters";
    public static final String CATEGORY_DESCRIPTION_NULL_OR_EMPTY_MESSAGE = "Category description cannot be null or empty";
    public static final String CATEGORY_DESCRIPTION_LENGTH_MESSAGE = "Category description cannot exceed 90 characters";
    public static final String CATEGORY_ALREADY_EXISTS_MESSAGE = "Category name must be unique";
}
