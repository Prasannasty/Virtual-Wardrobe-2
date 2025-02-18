package com.example.bitnbuild.service;

import com.example.bitnbuild.exception.OurException;
import com.example.bitnbuild.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom implementation of the UserDetailsService interface to load user details from the database.
 * This is used by Spring Security for authentication and authorization.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userrepo; // Repository to interact with user data in the database

    /**
     * Loads a user by their username (email in this case) from the database.
     * If the user is not found, it throws a custom exception.
     *
     * @param username The username (email) of the user to load
     * @return UserDetails The user details object that Spring Security will use
     * @throws UsernameNotFoundException If the user is not found in the database
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Attempt to find the user by email (username)
        return userrepo.findByEmail(username).orElseThrow(() -> new OurException("Username/email not found"));
    }
}
