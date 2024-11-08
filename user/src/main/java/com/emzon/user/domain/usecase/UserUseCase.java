package com.emzon.user.domain.usecase;

import com.emzon.user.domain.api.IUserServicePort;
import com.emzon.user.domain.model.User;
import com.emzon.user.domain.model.Role;
import com.emzon.user.domain.spi.IUserPersistencePort;
import com.emzon.user.domain.utilities.constants.UserUseCaseConstants;

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
}