package com.emzon.user.adapters.driving.http.dto.request;

import com.emzon.user.adapters.utilities.AddUserRequestConstants;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class AddUserRequest {
    public AddUserRequest(String name, String lastName, String documentId, String phone,
                          LocalDate birthDate, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.documentId = documentId;
        this.phone = phone;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
    }

    @NotBlank(message = AddUserRequestConstants.NAME_REQUIRED)
    @Pattern(regexp = AddUserRequestConstants.NAME_REGEX,
            message = AddUserRequestConstants.NAME_PATTERN_MESSAGE)
    private String name;

    @NotBlank(message = AddUserRequestConstants.LASTNAME_REQUIRED)
    @Pattern(regexp = AddUserRequestConstants.NAME_REGEX,
            message = AddUserRequestConstants.LASTNAME_PATTERN_MESSAGE)
    private String lastName;

    @NotBlank(message = AddUserRequestConstants.DOCUMENT_REQUIRED)
    @Pattern(regexp = AddUserRequestConstants.DOCUMENT_REGEX,
            message = AddUserRequestConstants.DOCUMENT_PATTERN_MESSAGE)
    private String documentId;

    @NotBlank(message = AddUserRequestConstants.PHONE_REQUIRED)
    @Pattern(regexp = AddUserRequestConstants.PHONE_REGEX,
            message = AddUserRequestConstants.PHONE_PATTERN_MESSAGE)
    @Size(max = AddUserRequestConstants.PHONE_MAX_LENGTH,
            message = AddUserRequestConstants.PHONE_SIZE_MESSAGE)
    private String phone;

    @NotNull(message = AddUserRequestConstants.BIRTHDATE_REQUIRED)
    @Past(message = AddUserRequestConstants.BIRTHDATE_PAST_MESSAGE)
    private LocalDate birthDate;

    @NotBlank(message = AddUserRequestConstants.EMAIL_REQUIRED)
    @Email(message = AddUserRequestConstants.EMAIL_FORMAT_MESSAGE)
    @Pattern(regexp = AddUserRequestConstants.EMAIL_REGEX,
            message = AddUserRequestConstants.EMAIL_PATTERN_MESSAGE)
    private String email;

    @NotBlank(message = AddUserRequestConstants.PASSWORD_REQUIRED)
    @Size(min = AddUserRequestConstants.PASSWORD_MIN_LENGTH,
            message = AddUserRequestConstants.PASSWORD_SIZE_MESSAGE)
    @Pattern(regexp = AddUserRequestConstants.PASSWORD_REGEX,
            message = AddUserRequestConstants.PASSWORD_PATTERN_MESSAGE)
    private String password;

    // Getters and setters remain the same
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}