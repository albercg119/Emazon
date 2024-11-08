package com.emzon.user.domain.utilities.constants;

public class UserUseCaseConstants {

    public static final String ADULT_AGE_ERROR = "El usuario debe ser mayor de edad";
    public static final String EMAIL_EXISTS_ERROR = "El correo electrónico ya se encuentra registrado";
    public static final String DOCUMENT_EXISTS_ERROR = "El número de documento ya se encuentra registrado";

    public static final String USER_NOT_FOUND = "Usuario no encontrado con el correo: ";
    public static final String EMAIL_ALREADY_EXISTS = "El correo electrónico ya existe";
    public static final String DOCUMENT_ALREADY_EXISTS = "El documento de identidad ya existe";

    public static final int MINIMUM_AGE = 18;
    public static final int MINIMUM_COUNT = 0;

    public static final String INVALID_ROLE = "Rol de usuario inválido";
    public static final String ROLE_NOT_ASSIGNED = "No se ha asignado un rol al usuario";

    public static final String EMAIL_REQUIRED = "El correo electrónico es requerido";
    public static final String DOCUMENT_REQUIRED = "El documento de identidad es requerido";
    public static final String PASSWORD_REQUIRED = "La contraseña es requerida";
    public static final String NAME_REQUIRED = "El nombre es requerido";
    public static final String DOCUMENT_ID_EXISTS = "El documento de identidad ya existe";

    private UserUseCaseConstants() {
        throw new IllegalStateException("Utility class");
    }
}