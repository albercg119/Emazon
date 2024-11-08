package com.emzon.user.domain.usecase;

import com.emzon.user.domain.api.IUserServicePort;
import com.emzon.user.domain.model.User;
import com.emzon.user.domain.model.Role;
import com.emzon.user.domain.spi.IUserPersistencePort;
import com.emzon.user.domain.utilities.constants.UserUseCaseConstants;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void createAuxBodegaUser(User user) {
        if (!user.isAdult()) {
            throw new IllegalArgumentException(UserUseCaseConstants.ADULT_AGE_ERROR);
        }

        if (userPersistencePort.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException(UserUseCaseConstants.EMAIL_EXISTS_ERROR);
        }

        if (userPersistencePort.existsByDocumentId(user.getDocumentId())) {
            throw new IllegalArgumentException(UserUseCaseConstants.DOCUMENT_EXISTS_ERROR);
        }

        user.setRole(Role.AUX_BODEGA);
        userPersistencePort.saveUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userPersistencePort.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(UserUseCaseConstants.USER_NOT_FOUND + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }

    public boolean isAdmin(String email) {
        User user = userPersistencePort.findByEmail(email);
        return user != null && user.getRole() == Role.ADMIN;
    }

    public boolean isAuxBodega(String email) {
        User user = userPersistencePort.findByEmail(email);
        return user != null && user.getRole() == Role.AUX_BODEGA;
    }

    public boolean isClient(String email) {
        User user = userPersistencePort.findByEmail(email);
        return user != null && user.getRole() == Role.CLIENT;
    }

    @Override
    public boolean existsAnyUser() {
        return userPersistencePort.countUsers() > UserUseCaseConstants.MINIMUM_COUNT;
    }

    @Override
    public void createUser(User user) {
        if (userPersistencePort.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException(UserUseCaseConstants.EMAIL_ALREADY_EXISTS);
        }

        if (userPersistencePort.existsByDocumentId(user.getDocumentId())) {
            throw new IllegalArgumentException(UserUseCaseConstants.DOCUMENT_ID_EXISTS);
        }

        userPersistencePort.saveUser(user);
    }

    @Override
    public long countUsers() {
        return userPersistencePort.countUsers();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userPersistencePort.existsByEmail(email);
    }
}