package com.Emazon.Stock.adapters.utilities;

public class CategoryControllerConstants {
    private CategoryControllerConstants() {
        throw new IllegalStateException("Utility class");
    }


    public static final String CATEGORY_BASE_PATH = "/category";
    public static final String CATEGORY_POST_PATH = "/";


    public static final String CATEGORY_SUCCESS_CODE = "201";
    public static final String CATEGORY_BAD_REQUEST_CODE = "400";
    public static final String CATEGORY_NOT_FOUND_CODE = "404";
    public static final String CATEGORY_CONFLICT_CODE = "409";


    public static final String CATEGORY_CREATED_SUMMARY = "Create a new category";


    public static final String CATEGORY_OPERATION_DESCRIPTION = "Creates a new category in the system";


    public static final String CATEGORY_CREATED_SUCCESSFULLY = "Category created successfully";


    public static final String CATEGORY_SUCCESS_RESPONSE = "Category was created successfully";
    public static final String CATEGORY_BAD_REQUEST_RESPONSE = "Invalid category data provided";
    public static final String CATEGORY_CONFLICT_RESPONSE = "Category already exists";


    public static final String CATEGORY_REQUEST_PARAM_DESC = "Category data to be created";


    public static final String CATEGORY_CONTROLLER_TAG = "Category Controller";
    public static final String CATEGORY_CONTROLLER_TAG_DESC = "APIs for managing categories";
}