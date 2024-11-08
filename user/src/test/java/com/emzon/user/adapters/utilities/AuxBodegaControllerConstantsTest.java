package com.emzon.user.adapters.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuxBodegaControllerConstantsTest {

    @Test
    void testPathConstants() {
        assertEquals("/api/aux-bodega", AuxBodegaControllerConstants.BASE_PATH);
        assertEquals("/supply", AuxBodegaControllerConstants.SUPPLY_PATH);
    }

    @Test
    void testOperationDetails() {
        assertEquals("Add supply", AuxBodegaControllerConstants.ADD_SUPPLY_SUMMARY);
        assertEquals("Adds new supply to inventory. Requires warehouse auxiliary role.",
                AuxBodegaControllerConstants.ADD_SUPPLY_DESCRIPTION);
    }

    @Test
    void testResponseCodes() {
        assertEquals("200", AuxBodegaControllerConstants.RESPONSE_200_CODE);
        assertEquals("403", AuxBodegaControllerConstants.RESPONSE_403_CODE);
        assertEquals("500", AuxBodegaControllerConstants.RESPONSE_500_CODE);
    }

    @Test
    void testResponseMessages() {
        assertEquals("Supply added successfully", AuxBodegaControllerConstants.RESPONSE_200_MESSAGE);
        assertEquals("Access denied - Not a warehouse auxiliary", AuxBodegaControllerConstants.RESPONSE_403_MESSAGE);
        assertEquals("Internal server error", AuxBodegaControllerConstants.RESPONSE_500_MESSAGE);
    }

    @Test
    void testResponseBodies() {
        assertEquals("Acceso denegado", AuxBodegaControllerConstants.ACCESS_DENIED_BODY);
        assertEquals("Suministro agregado", AuxBodegaControllerConstants.SUPPLY_ADDED_BODY);
    }
}