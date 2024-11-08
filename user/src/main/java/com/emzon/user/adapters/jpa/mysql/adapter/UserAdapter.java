package com.emzon.user.adapters.jpa.mysql.adapter;

import com.emzon.user.adapters.jpa.mysql.adapter.entity.UserEntity;
import com.emzon.user.adapters.jpa.mysql.mapper.IUserEntityMapper;
import com.emzon.user.adapters.jpa.mysql.repository.IUserRepository;
import com.emzon.user.domain.model.User;
import com.emzon.user.domain.spi.IUserPersistencePort;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;

    public UserAdapter(IUserRepository userRepository, IUserEntityMapper userEntityMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(User user) {
        UserEntity userEntity = userEntityMapper.toEntity(user);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public boolean existsByEmail(String email) {

        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByDocumentId(String documentId) {
        return userRepository.existsByDocumentId(documentId);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userEntityMapper::toDomain)
                .orElse(null);
    }

    @Override
    public long countUsers() {
        return userRepository.count();
    }

}