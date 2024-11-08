package com.emzon.user.adapters.utilities;

public class ClientControllerConstants {

    public static final String BASE_PATH = "/api/client";
    public static final String CART_ADD_PATH = "/cart/add";
    public static final String CART_REMOVE_PATH = "/cart/remove";
    public static final String PURCHASE_PATH = "/purchase";

    public static final String ADD_TO_CART_SUMMARY = "Add to cart";
    public static final String REMOVE_FROM_CART_SUMMARY = "Remove from cart";
    public static final String MAKE_PURCHASE_SUMMARY = "Make purchase";

    public static final String ADD_TO_CART_DESCRIPTION = "Adds a product to the user's shopping cart. Requires client role.";
    public static final String REMOVE_FROM_CART_DESCRIPTION = "Removes a product from the user's shopping cart. Requires client role.";
    public static final String MAKE_PURCHASE_DESCRIPTION = "Processes a purchase from the user's cart. Requires client role.";

    public static final String RESPONSE_200_CODE = "200";
    public static final String RESPONSE_403_CODE = "403";
    public static final String RESPONSE_500_CODE = "500";

    public static final String RESPONSE_200_ADD_CART = "Product added to cart successfully";
    public static final String RESPONSE_200_REMOVE_CART = "Product removed from cart successfully";
    public static final String RESPONSE_200_PURCHASE = "Purchase completed successfully";
    public static final String RESPONSE_403_MESSAGE = "Access denied - Not a client";
    public static final String RESPONSE_500_MESSAGE = "Internal server error";

    public static final String ACCESS_DENIED_BODY = "Acceso denegado";
    public static final String PRODUCT_ADDED_BODY = "Producto agregado al carrito";
    public static final String PRODUCT_REMOVED_BODY = "Producto eliminado del carrito";
    public static final String PURCHASE_COMPLETED_BODY = "Compra realizada";
}