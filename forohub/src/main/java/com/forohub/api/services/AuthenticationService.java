package com.forohub.api.services;

import com.forohub.api.domain.user.UserRepository;
import com.forohub.api.dtos.authentication.AuthenticationRequestDTO;
import com.forohub.api.dtos.authentication.AuthenticationResponseDTO;
import com.forohub.api.domain.user.UserEntity;
import com.forohub.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserEntity) repository.findByUsername(username);
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = authenticationManager.authenticate(authToken);
        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
        return new AuthenticationResponseDTO(token);
    }
}
