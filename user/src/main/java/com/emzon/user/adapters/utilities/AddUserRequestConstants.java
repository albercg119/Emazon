package com.emzon.user.adapters.utilities;

public class AddUserRequestConstants {

    public static final String NAME_REQUIRED = "El nombre es requerido";
    public static final String NAME_PATTERN_MESSAGE = "El nombre solo debe contener letras y espacios";
    public static final String LASTNAME_REQUIRED = "El apellido es requerido";
    public static final String LASTNAME_PATTERN_MESSAGE = "El apellido solo debe contener letras y espacios";
    public static final String DOCUMENT_REQUIRED = "El documento de identidad es requerido";
    public static final String DOCUMENT_PATTERN_MESSAGE = "El documento debe contener únicamente números";
    public static final String PHONE_REQUIRED = "El teléfono es requerido";
    public static final String PHONE_PATTERN_MESSAGE = "El teléfono debe tener entre 10 y 12 dígitos y puede incluir el símbolo +";
    public static final String PHONE_SIZE_MESSAGE = "El teléfono no puede tener más de 13 caracteres";
    public static final String BIRTHDATE_REQUIRED = "La fecha de nacimiento es requerida";
    public static final String BIRTHDATE_PAST_MESSAGE = "La fecha de nacimiento debe ser en el pasado";
    public static final String EMAIL_REQUIRED = "El correo electrónico es requerido";
    public static final String EMAIL_FORMAT_MESSAGE = "El formato del correo electrónico no es válido";
    public static final String EMAIL_PATTERN_MESSAGE = "El correo electrónico debe tener un formato válido";
    public static final String PASSWORD_REQUIRED = "La contraseña es requerida";
    public static final String PASSWORD_SIZE_MESSAGE = "La contraseña debe tener al menos 8 caracteres";
    public static final String PASSWORD_PATTERN_MESSAGE = "La contraseña debe contener al menos un número, una letra minúscula, una letra mayúscula y un carácter especial";

    public static final String NAME_REGEX = "^[A-Za-zÁáÉéÍíÓóÚúÑñ\\s]+$";
    public static final String DOCUMENT_REGEX = "^[0-9]+$";
    public static final String PHONE_REGEX = "^\\+?[0-9]{10,12}$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";

    public static final int PHONE_MAX_LENGTH = 13;
    public static final int PASSWORD_MIN_LENGTH = 8;

    private AddUserRequestConstants() {
        throw new IllegalStateException("Utility class");
    }
}
