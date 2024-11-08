package com.emzon.user.configuration.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtValidationService {

    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService jwtUserDetailsService;

    public JwtValidationService(JwtTokenUtil jwtTokenUtil, JwtUserDetailsService jwtUserDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    public UserDetails validateTokenAndGetUserDetails(String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.validateToken(token, userDetails)) {
            return userDetails;
        }
        return null;
    }
}