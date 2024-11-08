package com.emzon.user.adapters.driving.http.mapper;

import com.emzon.user.adapters.driving.http.dto.response.UserResponse;
import com.emzon.user.domain.model.Role;
import com.emzon.user.domain.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class IUserResponseMapperTest {

    private final IUserResponseMapper mapper = Mappers.getMapper(IUserResponseMapper.class);

    @Test
    void toResponse_ShouldMapAllFieldsCorrectly() {
        // Arrange
        User user = new User(
                1L,
                "Juan",
                "Pérez",
                "1234567890",
                "+573005698325",
                LocalDate.now().minusYears(20),
                "juan.perez@example.com",
                "password123",
                Role.AUX_BODEGA
        );

        // Act
        UserResponse result = mapper.toResponse(user);

        // Assert
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getLastName(), result.getLastName());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getRole().toString(), result.getRole());
    }

    @Test
    void toResponse_WithNullUser_ShouldReturnNull() {
        // Act
        UserResponse result = mapper.toResponse(null);

        // Assert
        assertNull(result);
    }
}