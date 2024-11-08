package com.emzon.user.domain.api;

import com.emzon.user.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserServicePort {
    void createAuxBodegaUser(User user);
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
    boolean isAdmin(String email);
    boolean isAuxBodega(String email);
    boolean isClient(String email);
    boolean existsAnyUser();
    void createUser(User user);
    long countUsers();
    boolean existsByEmail(String email);
}