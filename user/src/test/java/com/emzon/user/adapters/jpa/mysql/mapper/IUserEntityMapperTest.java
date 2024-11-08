package com.emzon.user.adapters.jpa.mysql.mapper;

import com.emzon.user.adapters.jpa.mysql.adapter.entity.UserEntity;
import com.emzon.user.domain.model.Role;
import com.emzon.user.domain.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class IUserEntityMapperTest {

    private final IUserEntityMapper mapper = Mappers.getMapper(IUserEntityMapper.class);
    private final LocalDate birthDate = LocalDate.now().minusYears(20);

    @Test
    void toEntity_DeberiaMapearTodosLosCamposCorrectamente() {
        // Arrange
        User user = new User(
                1L, "Juan", "Pérez", "1234567890",
                "+573005698325", birthDate, "juan@example.com",
                "password123", Role.AUXILIARY_WAREHOUSE
        );

        // Act
        UserEntity result = mapper.toEntity(user);

        // Assert
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getLastName(), result.getLastName());
        assertEquals(user.getDocumentId(), result.getDocumentId());
        assertEquals(user.getPhone(), result.getPhone());
        assertEquals(user.getBirthDate(), result.getBirthDate());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getPassword(), result.getPassword());
        assertEquals(user.getRole(), result.getRole());
    }

    @Test
    void toDomain_DeberiaMapearTodosLosCamposCorrectamente() {
        // Arrange
        UserEntity entity = new UserEntity(
                1L, "Juan", "Pérez", "1234567890",
                "+573005698325", birthDate, "juan@example.com",
                "password123", Role.AUXILIARY_WAREHOUSE
        );

        // Act
        User result = mapper.toDomain(entity);

        // Assert
        assertNotNull(result);
        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getName(), result.getName());
        assertEquals(entity.getLastName(), result.getLastName());
        assertEquals(entity.getDocumentId(), result.getDocumentId());
        assertEquals(entity.getPhone(), result.getPhone());
        assertEquals(entity.getBirthDate(), result.getBirthDate());
        assertEquals(entity.getEmail(), result.getEmail());
        assertEquals(entity.getPassword(), result.getPassword());
        assertEquals(entity.getRole(), result.getRole());
    }

    @Test
    void mapper_ConValoresNulos_DeberiaMapearCorrectamente() {
        // Arrange
        User user = new User(null, null, null, null,
                null, null, null, null, null);

        // Act
        UserEntity entity = mapper.toEntity(user);
        User domainResult = mapper.toDomain(entity);

        // Assert
        assertNotNull(entity);
        assertNotNull(domainResult);

        assertNull(entity.getId());
        assertNull(entity.getName());
        assertNull(entity.getLastName());
        assertNull(entity.getDocumentId());
        assertNull(entity.getPhone());
        assertNull(entity.getBirthDate());
        assertNull(entity.getEmail());
        assertNull(entity.getPassword());
        assertNull(entity.getRole());

        assertNull(domainResult.getId());
        assertNull(domainResult.getName());
        assertNull(domainResult.getLastName());
        assertNull(domainResult.getDocumentId());
        assertNull(domainResult.getPhone());
        assertNull(domainResult.getBirthDate());
        assertNull(domainResult.getEmail());
        assertNull(domainResult.getPassword());
        assertNull(domainResult.getRole());
    }
}