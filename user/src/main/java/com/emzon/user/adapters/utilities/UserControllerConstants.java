package com.emzon.user.adapters.utilities;

public class UserControllerConstants {

    public static final String BASE_PATH = "/users";
    public static final String CREATE_PATH = "/create";

    public static final String TAG_NAME = "Users";
    public static final String TAG_DESCRIPTION = "API para la gestión de usuarios";
    public static final String OPERATION_SUMMARY = "Crear un nuevo usuario auxiliar de bodega";
    public static final String OPERATION_DESCRIPTION = "Crea un nuevo usuario con rol de auxiliar de bodega en el sistema";

    public static final String RESPONSE_201_CODE = "201";
    public static final String RESPONSE_201_MESSAGE = "Usuario creado exitosamente";
    public static final String RESPONSE_400_CODE = "400";
    public static final String RESPONSE_400_MESSAGE = "Datos de entrada inválidos";
    public static final String RESPONSE_500_CODE = "500";
    public static final String RESPONSE_500_MESSAGE = "Error interno del servidor";

    private UserControllerConstants() {
        throw new IllegalStateException("Utility class");
    }
}