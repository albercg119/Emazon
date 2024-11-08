package com.emzon.user.domain.utilities.constants;

public class UserUseCaseConstants {

    public static final String ADULT_AGE_ERROR = "El usuario debe ser mayor de edad";
    public static final String EMAIL_EXISTS_ERROR = "El correo electrónico ya se encuentra registrado";
    public static final String DOCUMENT_EXISTS_ERROR = "El número de documento ya se encuentra registrado";

    public static final int MINIMUM_AGE = 18;

    private UserUseCaseConstants() {
        throw new IllegalStateException("Utility class");
    }
}
