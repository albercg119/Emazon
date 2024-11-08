package com.emzon.user.configuration;

import com.emzon.user.adapters.jpa.mysql.adapter.UserAdapter;
import com.emzon.user.adapters.jpa.mysql.mapper.IUserEntityMapper;
import com.emzon.user.adapters.jpa.mysql.repository.IUserRepository;
import com.emzon.user.domain.api.IUserServicePort;
import com.emzon.user.domain.spi.IUserPersistencePort;
import com.emzon.user.domain.usecase.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Bean
    public IUserPersistencePort userPersistencePort(IUserRepository userRepository, IUserEntityMapper userEntityMapper, PasswordEncoder passwordEncoder) {
        return new UserAdapter(userRepository, userEntityMapper, passwordEncoder);
    }

    @Bean
    public IUserServicePort userServicePort(IUserPersistencePort userPersistencePort) {
        return new UserUseCase(userPersistencePort);
    }
}