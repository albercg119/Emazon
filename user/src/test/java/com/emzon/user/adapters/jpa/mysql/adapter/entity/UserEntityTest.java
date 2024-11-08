package com.emzon.user.adapters.jpa.mysql.adapter.entity;

import com.emzon.user.domain.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EnumType;
import javax.persistence.GenerationType;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    private UserEntity userEntity;
    private static final Long TEST_ID = 1L;
    private static final String TEST_NAME = "Juan";
    private static final String TEST_LAST_NAME = "Pérez";
    private static final String TEST_DOCUMENT_ID = "1234567890";
    private static final String TEST_PHONE = "+573005698325";
    private static final LocalDate TEST_BIRTH_DATE = LocalDate.now().minusYears(20);
    private static final String TEST_EMAIL = "juan.perez@example.com";
    private static final String TEST_PASSWORD = "password123";
    private static final Role TEST_ROLE = Role.AUXILIARY_WAREHOUSE;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity(
                TEST_ID,
                TEST_NAME,
                TEST_LAST_NAME,
                TEST_DOCUMENT_ID,
                TEST_PHONE,
                TEST_BIRTH_DATE,
                TEST_EMAIL,
                TEST_PASSWORD,
                TEST_ROLE
        );
    }

    @Test
    void constructor_DeberiaInicializarTodosLosCampos() {
        assertEquals(TEST_ID, userEntity.getId());
        assertEquals(TEST_NAME, userEntity.getName());
        assertEquals(TEST_LAST_NAME, userEntity.getLastName());
        assertEquals(TEST_DOCUMENT_ID, userEntity.getDocumentId());
        assertEquals(TEST_PHONE, userEntity.getPhone());
        assertEquals(TEST_BIRTH_DATE, userEntity.getBirthDate());
        assertEquals(TEST_EMAIL, userEntity.getEmail());
        assertEquals(TEST_PASSWORD, userEntity.getPassword());
        assertEquals(TEST_ROLE, userEntity.getRole());
    }

    @Test
    void setAndGetId_DeberiaFuncionarCorrectamente() {
        Long newId = 2L;
        userEntity.setId(newId);
        assertEquals(newId, userEntity.getId());
    }

    @Test
    void setAndGetName_DeberiaFuncionarCorrectamente() {
        String newName = "Carlos";
        userEntity.setName(newName);
        assertEquals(newName, userEntity.getName());
    }

    @Test
    void setAndGetLastName_DeberiaFuncionarCorrectamente() {
        String newLastName = "García";
        userEntity.setLastName(newLastName);
        assertEquals(newLastName, userEntity.getLastName());
    }

    @Test
    void setAndGetDocumentId_DeberiaFuncionarCorrectamente() {
        String newDocumentId = "9876543210";
        userEntity.setDocumentId(newDocumentId);
        assertEquals(newDocumentId, userEntity.getDocumentId());
    }

    @Test
    void setAndGetPhone_DeberiaFuncionarCorrectamente() {
        String newPhone = "+573219876543";
        userEntity.setPhone(newPhone);
        assertEquals(newPhone, userEntity.getPhone());
    }

    @Test
    void setAndGetBirthDate_DeberiaFuncionarCorrectamente() {
        LocalDate newBirthDate = LocalDate.now().minusYears(25);
        userEntity.setBirthDate(newBirthDate);
        assertEquals(newBirthDate, userEntity.getBirthDate());
    }

    @Test
    void setAndGetEmail_DeberiaFuncionarCorrectamente() {
        String newEmail = "carlos.garcia@example.com";
        userEntity.setEmail(newEmail);
        assertEquals(newEmail, userEntity.getEmail());
    }

    @Test
    void setAndGetPassword_DeberiaFuncionarCorrectamente() {
        String newPassword = "newPassword123";
        userEntity.setPassword(newPassword);
        assertEquals(newPassword, userEntity.getPassword());
    }

    @Test
    void setAndGetRole_DeberiaFuncionarCorrectamente() {
        Role newRole = Role.ADMIN;
        userEntity.setRole(newRole);
        assertEquals(newRole, userEntity.getRole());
    }

    @Test
    void constructor_ConValoresNulos_DeberiaPermitirlos() {
        UserEntity nullEntity = new UserEntity(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        assertNull(nullEntity.getId());
        assertNull(nullEntity.getName());
        assertNull(nullEntity.getLastName());
        assertNull(nullEntity.getDocumentId());
        assertNull(nullEntity.getPhone());
        assertNull(nullEntity.getBirthDate());
        assertNull(nullEntity.getEmail());
        assertNull(nullEntity.getPassword());
        assertNull(nullEntity.getRole());
    }

    @Test
    void setters_ConValoresNulos_DeberiaPermitirlos() {
        userEntity.setId(null);
        userEntity.setName(null);
        userEntity.setLastName(null);
        userEntity.setDocumentId(null);
        userEntity.setPhone(null);
        userEntity.setBirthDate(null);
        userEntity.setEmail(null);
        userEntity.setPassword(null);
        userEntity.setRole(null);

        assertNull(userEntity.getId());
        assertNull(userEntity.getName());
        assertNull(userEntity.getLastName());
        assertNull(userEntity.getDocumentId());
        assertNull(userEntity.getPhone());
        assertNull(userEntity.getBirthDate());
        assertNull(userEntity.getEmail());
        assertNull(userEntity.getPassword());
        assertNull(userEntity.getRole());
    }

    @Test
    void verificarAnotaciones() {
        // Verificar anotación @Entity
        assertTrue(UserEntity.class.isAnnotationPresent(javax.persistence.Entity.class));

        // Verificar anotación @Table y su nombre
        assertTrue(UserEntity.class.isAnnotationPresent(javax.persistence.Table.class));
        assertEquals("users",
                UserEntity.class.getAnnotation(javax.persistence.Table.class).name());

        // Verificar anotaciones del ID
        try {
            var idField = UserEntity.class.getDeclaredField("id");
            assertTrue(idField.isAnnotationPresent(javax.persistence.Id.class));
            assertTrue(idField.isAnnotationPresent(javax.persistence.GeneratedValue.class));
            assertEquals(GenerationType.IDENTITY,
                    idField.getAnnotation(javax.persistence.GeneratedValue.class).strategy());
        } catch (NoSuchFieldException e) {
            fail("Campo 'id' no encontrado");
        }

        // Verificar anotación @Enumerated en el campo role
        try {
            var roleField = UserEntity.class.getDeclaredField("role");
            assertTrue(roleField.isAnnotationPresent(javax.persistence.Enumerated.class));
            assertEquals(EnumType.STRING,
                    roleField.getAnnotation(javax.persistence.Enumerated.class).value());
        } catch (NoSuchFieldException e) {
            fail("Campo 'role' no encontrado");
        }
    }
}