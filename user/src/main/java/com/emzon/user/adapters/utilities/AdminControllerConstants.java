package com.emzon.user.adapters.utilities;

public class AdminControllerConstants {

    public static final String BASE_PATH = "/api/admin";
    public static final String CATEGORY_PATH = "/category";
    public static final String BRAND_PATH = "/brand";
    public static final String ARTICLE_PATH = "/article";
    public static final String AUX_BODEGA_PATH = "/aux-bodega";

    public static final String CREATE_CATEGORY_SUMMARY = "Create new category";
    public static final String CREATE_BRAND_SUMMARY = "Create new brand";
    public static final String CREATE_ARTICLE_SUMMARY = "Create new article";
    public static final String CREATE_AUX_BODEGA_SUMMARY = "Create warehouse auxiliary user";

    public static final String CREATE_CATEGORY_DESCRIPTION = "Creates a new category. Requires admin role.";
    public static final String CREATE_BRAND_DESCRIPTION = "Creates a new brand. Requires admin role.";
    public static final String CREATE_ARTICLE_DESCRIPTION = "Creates a new article. Requires admin role.";
    public static final String CREATE_AUX_BODEGA_DESCRIPTION = "Creates a new warehouse auxiliary user. Requires admin role.";

    public static final String RESPONSE_200_CODE = "200";
    public static final String RESPONSE_403_CODE = "403";
    public static final String RESPONSE_500_CODE = "500";

    public static final String RESPONSE_200_CATEGORY = "Category created successfully";
    public static final String RESPONSE_200_BRAND = "Brand created successfully";
    public static final String RESPONSE_200_ARTICLE = "Article created successfully";
    public static final String RESPONSE_200_AUX_BODEGA = "Warehouse auxiliary user created successfully";
    public static final String RESPONSE_403_MESSAGE = "Access denied - Not an admin";
    public static final String RESPONSE_500_MESSAGE = "Internal server error";

    public static final String ACCESS_DENIED_BODY = "Acceso denegado";
    public static final String CATEGORY_CREATED_BODY = "Categoría creada";
    public static final String BRAND_CREATED_BODY = "Marca creada";
    public static final String ARTICLE_CREATED_BODY = "Artículo creado";
    public static final String AUX_BODEGA_CREATED_BODY = "Usuario auxiliar de bodega creado";
}
