// Package declaration for the security configuration
package com.example.bitnbuild.security;

// Import necessary classes for JWT authentication, user details, and filter handling
import com.example.bitnbuild.service.CustomUserDetailsService;
import com.example.bitnbuild.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Mark this class as a Spring-managed component
@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    // Injecting the utility class for working with JWT tokens
    @Autowired
    private JWTUtils jwtUtils;

    // Injecting the custom service to load user details
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Filters incoming HTTP requests to validate JWT tokens and set authentication in the SecurityContext.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Retrieve the Authorization header from the request
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        // Check if the Authorization header is present and starts with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // If not, pass the request to the next filter in the chain
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the JWT token by removing the "Bearer " prefix
        jwtToken = authHeader.substring(7);

        // Extract the username (email) from the token using JWTUtils
        userEmail = jwtUtils.extractUsername(jwtToken);

        // Check if the username is not null and there is no existing authentication in the SecurityContext
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load the user details from the database or custom service
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);

            // Validate the token using the user details
            if (jwtUtils.isValid(jwtToken, userDetails)) {
                // Create an empty SecurityContext
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                // Create an authentication token using the user details
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                // Set additional details for the token (e.g., request details)
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the authentication in the SecurityContext
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }
        }

        // Pass the request to the next filter in the chain
        filterChain.doFilter(request, response);
    }
}
