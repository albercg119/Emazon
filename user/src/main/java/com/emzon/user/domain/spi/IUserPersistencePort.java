package com.emzon.user.domain.spi;

import com.emzon.user.domain.model.User;

public interface IUserPersistencePort {
    void saveUser(User user);
    boolean existsByEmail(String email);
    boolean existsByDocumentId(String documentId);
    User findByEmail(String email);
    long countUsers();
}