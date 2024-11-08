package com.emzon.user.adapters.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdminControllerConstantsTest {

    @Test
    void testPathConstants() {
        assertEquals("/api/admin", AdminControllerConstants.BASE_PATH);
        assertEquals("/category", AdminControllerConstants.CATEGORY_PATH);
        assertEquals("/brand", AdminControllerConstants.BRAND_PATH);
        assertEquals("/article", AdminControllerConstants.ARTICLE_PATH);
        assertEquals("/aux-bodega", AdminControllerConstants.AUX_BODEGA_PATH);
    }

    @Test
    void testOperationSummaries() {
        assertEquals("Create new category", AdminControllerConstants.CREATE_CATEGORY_SUMMARY);
        assertEquals("Create new brand", AdminControllerConstants.CREATE_BRAND_SUMMARY);
        assertEquals("Create new article", AdminControllerConstants.CREATE_ARTICLE_SUMMARY);
        assertEquals("Create warehouse auxiliary user", AdminControllerConstants.CREATE_AUX_BODEGA_SUMMARY);
    }

    @Test
    void testOperationDescriptions() {
        assertEquals("Creates a new category. Requires admin role.", AdminControllerConstants.CREATE_CATEGORY_DESCRIPTION);
        assertEquals("Creates a new brand. Requires admin role.", AdminControllerConstants.CREATE_BRAND_DESCRIPTION);
        assertEquals("Creates a new article. Requires admin role.", AdminControllerConstants.CREATE_ARTICLE_DESCRIPTION);
        assertEquals("Creates a new warehouse auxiliary user. Requires admin role.", AdminControllerConstants.CREATE_AUX_BODEGA_DESCRIPTION);
    }

    @Test
    void testResponseCodes() {
        assertEquals("200", AdminControllerConstants.RESPONSE_200_CODE);
        assertEquals("403", AdminControllerConstants.RESPONSE_403_CODE);
        assertEquals("500", AdminControllerConstants.RESPONSE_500_CODE);
    }

    @Test
    void testResponseMessages() {
        assertEquals("Category created successfully", AdminControllerConstants.RESPONSE_200_CATEGORY);
        assertEquals("Brand created successfully", AdminControllerConstants.RESPONSE_200_BRAND);
        assertEquals("Article created successfully", AdminControllerConstants.RESPONSE_200_ARTICLE);
        assertEquals("Warehouse auxiliary user created successfully", AdminControllerConstants.RESPONSE_200_AUX_BODEGA);
        assertEquals("Access denied - Not an admin", AdminControllerConstants.RESPONSE_403_MESSAGE);
        assertEquals("Internal server error", AdminControllerConstants.RESPONSE_500_MESSAGE);
    }

    @Test
    void testResponseBodies() {
        assertEquals("Acceso denegado", AdminControllerConstants.ACCESS_DENIED_BODY);
        assertEquals("Categoría creada", AdminControllerConstants.CATEGORY_CREATED_BODY);
        assertEquals("Marca creada", AdminControllerConstants.BRAND_CREATED_BODY);
        assertEquals("Artículo creado", AdminControllerConstants.ARTICLE_CREATED_BODY);
        assertEquals("Usuario auxiliar de bodega creado", AdminControllerConstants.AUX_BODEGA_CREATED_BODY);
    }
}