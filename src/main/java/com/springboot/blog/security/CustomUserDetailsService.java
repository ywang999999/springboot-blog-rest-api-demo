package com.springboot.blog.security;

import com.springboot.blog.entity.User;
import com.springboot.blog.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/*
UserDetailsService is an interface that is used to retrieve user-related data.
It's a core interface that provides a way for Spring Security to interact with your user data store,
whether it's a relational database, LDAP server, or any other source.
 */

//UserDetailsService exists already: package org.springframework.security.core
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));

        Set<GrantedAuthority> autorities = user
                .getRoles()
                .stream()
                .map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        //public class User implements UserDetails, CredentialsContainer
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                autorities);
    }
}
