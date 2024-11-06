package com.Emazon.Stock.adapters.utilities;

public final class ArticleMapperConstants {
    private ArticleMapperConstants() {
        throw new IllegalStateException(UTILITY_CLASS_ERROR);
    }

    public static final String ID_FIELD = "id";
    public static final String NAME_FIELD = "name";
    public static final String DESCRIPTION_FIELD = "description";
    public static final String PRICE_FIELD = "price";
    public static final String BRAND_NOMBRE_FIELD = "brand.nombre";
    public static final String CATEGORIES_FIELD = "categories";
    public static final String CATEGORY_IDS_FIELD = "categoryIds";
    public static final String BRAND_ID_FIELD = "brandId";

    public static final String BRAND_NAME_TARGET = "brandName";
    public static final String BRAND_TARGET = "brand";
    public static final String CATEGORIES_TARGET = "categories";

    public static final String MAP_CATEGORIES_METHOD = "mapCategories";
    public static final String MAP_CATEGORY_IDS_METHOD = "mapCategoryIds";
    public static final String MAP_BRAND_ID_METHOD = "mapBrandId";

    public static final String CATEGORY_MAPPING_ERROR = "Error mapping categories";
    public static final String BRAND_MAPPING_ERROR = "Error mapping brand";

    public static final String UTILITY_CLASS_ERROR = "Utility class";
}