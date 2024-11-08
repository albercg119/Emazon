package com.emzon.user.adapters.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClientControllerConstantsTest {

    @Test
    void testPathConstants() {
        assertEquals("/api/client", ClientControllerConstants.BASE_PATH);
        assertEquals("/cart/add", ClientControllerConstants.CART_ADD_PATH);
        assertEquals("/cart/remove", ClientControllerConstants.CART_REMOVE_PATH);
        assertEquals("/purchase", ClientControllerConstants.PURCHASE_PATH);
    }

    @Test
    void testOperationSummaries() {
        assertEquals("Add to cart", ClientControllerConstants.ADD_TO_CART_SUMMARY);
        assertEquals("Remove from cart", ClientControllerConstants.REMOVE_FROM_CART_SUMMARY);
        assertEquals("Make purchase", ClientControllerConstants.MAKE_PURCHASE_SUMMARY);
    }

    @Test
    void testOperationDescriptions() {
        assertEquals("Adds a product to the user's shopping cart. Requires client role.",
                ClientControllerConstants.ADD_TO_CART_DESCRIPTION);
        assertEquals("Removes a product from the user's shopping cart. Requires client role.",
                ClientControllerConstants.REMOVE_FROM_CART_DESCRIPTION);
        assertEquals("Processes a purchase from the user's cart. Requires client role.",
                ClientControllerConstants.MAKE_PURCHASE_DESCRIPTION);
    }

    @Test
    void testResponseCodes() {
        assertEquals("200", ClientControllerConstants.RESPONSE_200_CODE);
        assertEquals("403", ClientControllerConstants.RESPONSE_403_CODE);
        assertEquals("500", ClientControllerConstants.RESPONSE_500_CODE);
    }

    @Test
    void testResponseMessages() {
        assertEquals("Product added to cart successfully", ClientControllerConstants.RESPONSE_200_ADD_CART);
        assertEquals("Product removed from cart successfully", ClientControllerConstants.RESPONSE_200_REMOVE_CART);
        assertEquals("Purchase completed successfully", ClientControllerConstants.RESPONSE_200_PURCHASE);
        assertEquals("Access denied - Not a client", ClientControllerConstants.RESPONSE_403_MESSAGE);
        assertEquals("Internal server error", ClientControllerConstants.RESPONSE_500_MESSAGE);
    }

    @Test
    void testResponseBodies() {
        assertEquals("Acceso denegado", ClientControllerConstants.ACCESS_DENIED_BODY);
        assertEquals("Producto agregado al carrito", ClientControllerConstants.PRODUCT_ADDED_BODY);
        assertEquals("Producto eliminado del carrito", ClientControllerConstants.PRODUCT_REMOVED_BODY);
        assertEquals("Compra realizada", ClientControllerConstants.PURCHASE_COMPLETED_BODY);
    }
}