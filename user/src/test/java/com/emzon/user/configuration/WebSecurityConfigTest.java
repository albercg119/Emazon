package com.emzon.user.configuration;

import com.emzon.user.configuration.utilities.constants.WebSecurityConfigConstants;
import com.emzon.user.adapters.driving.http.dto.request.AddUserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class WebSecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private AddUserRequest validUserRequest;

    @BeforeEach
    void setUp() {
        // Generar email único para evitar duplicados
        String uniqueEmail = "test" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";

        validUserRequest = new AddUserRequest(
                "testUser",      // name
                "testPassword",  // lastName
                "DOC" + UUID.randomUUID().toString().substring(0, 8),  // documentId único
                "1234567890",   // phone
                LocalDate.of(2000, 1, 1), // fecha que garantiza mayor de edad
                uniqueEmail,    // email único
                "password123"   // password
        );
    }

    @Test
    @DisplayName("Should create user successfully - Happy Path")
    void testUserCreation() throws Exception {
        mockMvc.perform(post(WebSecurityConfigConstants.USER_CREATE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validUserRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("testUser"))
                .andExpect(jsonPath("$.email").value(validUserRequest.getEmail()))
                .andExpect(jsonPath("$.role").value("AUX_BODEGA"));
    }

    @Test
    @DisplayName("Should reject duplicate email")
    void testDuplicateEmailRejection() throws Exception {
        // Primera creación del usuario
        mockMvc.perform(post(WebSecurityConfigConstants.USER_CREATE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validUserRequest)))
                .andDo(print())
                .andExpect(status().isCreated());

        // Intento duplicado
        mockMvc.perform(post(WebSecurityConfigConstants.USER_CREATE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validUserRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("El correo electrónico ya se encuentra registrado"))
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.description").value("uri=/users/create"));
    }

    @Test
    @DisplayName("Should reject invalid request format")
    void testInvalidRequestFormat() throws Exception {
        String invalidJson = "{invalid-json}";

        mockMvc.perform(post(WebSecurityConfigConstants.USER_CREATE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should reject request with invalid content type")
    void testInvalidContentType() throws Exception {
        mockMvc.perform(post(WebSecurityConfigConstants.USER_CREATE_ENDPOINT)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(objectMapper.writeValueAsString(validUserRequest)))
                .andDo(print())
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    @DisplayName("Should allow access without authentication")
    void testPublicAccess() throws Exception {
        // Verificar que el endpoint es accesible sin autenticación
        AddUserRequest newRequest = new AddUserRequest(
                "testUser2",
                "testPassword",
                "DOC" + UUID.randomUUID().toString().substring(0, 8),
                "1234567890",
                LocalDate.of(2000, 1, 1),
                "test" + UUID.randomUUID().toString().substring(0, 8) + "@example.com",
                "password123"
        );

        mockMvc.perform(post(WebSecurityConfigConstants.USER_CREATE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newRequest)))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}