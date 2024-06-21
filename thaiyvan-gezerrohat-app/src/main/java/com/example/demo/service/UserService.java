package com.example.demo.service;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Example;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserService {

    private static final String AUTHORITY_USER = "ROLE_USER";

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @Caching(
            evict = @CacheEvict(value = "findAllUsers", allEntries = true))
    public User registerUser(User user) {
        Set<Authority> authorities = new HashSet<>(Collections.singletonList(getAuthority()));
        user.setAuthorities(authorities);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    @Cacheable(value = "findAllUsers")
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public String loginAndGetToken(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            return tokenProvider.createToken(authentication);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid username or password");
        }
    }

    private Authority getAuthority() {
        return authorityRepository.findById(UserService.AUTHORITY_USER)
                .orElseGet(() -> {
                    Authority authority = new Authority();
                    authority.setAuthority(UserService.AUTHORITY_USER);
                    return authorityRepository.save(authority);
                });
    }
}
