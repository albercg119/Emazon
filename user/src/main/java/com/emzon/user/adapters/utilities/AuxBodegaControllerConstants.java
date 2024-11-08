package com.emzon.user.adapters.utilities;

public class AuxBodegaControllerConstants {

    public static final String BASE_PATH = "/api/aux-bodega";
    public static final String SUPPLY_PATH = "/supply";

    public static final String ADD_SUPPLY_SUMMARY = "Add supply";
    public static final String ADD_SUPPLY_DESCRIPTION = "Adds new supply to inventory. Requires warehouse auxiliary role.";

    public static final String RESPONSE_200_CODE = "200";
    public static final String RESPONSE_403_CODE = "403";
    public static final String RESPONSE_500_CODE = "500";

    public static final String RESPONSE_200_MESSAGE = "Supply added successfully";
    public static final String RESPONSE_403_MESSAGE = "Access denied - Not a warehouse auxiliary";
    public static final String RESPONSE_500_MESSAGE = "Internal server error";

    public static final String ACCESS_DENIED_BODY = "Acceso denegado";
    public static final String SUPPLY_ADDED_BODY = "Suministro agregado";
}
