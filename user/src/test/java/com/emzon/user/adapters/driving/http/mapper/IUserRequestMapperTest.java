package com.emzon.user.adapters.driving.http.mapper;

import com.emzon.user.adapters.driving.http.dto.request.AddUserRequest;
import com.emzon.user.domain.model.Role;
import com.emzon.user.domain.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class IUserRequestMapperTest {

    private final IUserRequestMapper mapper = Mappers.getMapper(IUserRequestMapper.class);

    @Test
    void toUser_ShouldMapAllFieldsCorrectly() {
        // Arrange
        AddUserRequest request = new AddUserRequest(
                "Juan",
                "Pérez",
                "1234567890",
                "+573005698325",
                LocalDate.now().minusYears(20),
                "juan.perez@example.com",
                "password123"
        );

        // Act
        User result = mapper.toUser(request);

        // Assert
        assertNotNull(result);
        assertNull(result.getId()); // Should be ignored in mapping
        assertEquals(request.getName(), result.getName());
        assertEquals(request.getLastName(), result.getLastName());
        assertEquals(request.getDocumentId(), result.getDocumentId());
        assertEquals(request.getPhone(), result.getPhone());
        assertEquals(request.getBirthDate(), result.getBirthDate());
        assertEquals(request.getEmail(), result.getEmail());
        assertEquals(request.getPassword(), result.getPassword());
        assertEquals(Role.AUXILIARY_WAREHOUSE, result.getRole()); // Comparamos con el enum
    }

    @Test
    void toUser_WithNullRequest_ShouldReturnNull() {
        // Act
        User result = mapper.toUser(null);

        // Assert
        assertNull(result);
    }

    @Test
    void toUser_ShouldSetDefaultValues() {
        // Arrange
        AddUserRequest request = new AddUserRequest(
                "Juan",
                "Pérez",
                "1234567890",
                "+573005698325",
                LocalDate.now().minusYears(20),
                "juan.perez@example.com",
                "password123"
        );

        // Act
        User result = mapper.toUser(request);

        // Assert
        assertNotNull(result);
        // Verificar valores por defecto
        assertNull(result.getId()); // Debe ser ignorado
        assertEquals(Role.AUXILIARY_WAREHOUSE, result.getRole()); // Debe tener el rol por defecto
    }
}