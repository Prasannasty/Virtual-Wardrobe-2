package com.example.bitnbuild.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication; // Represents the authenticated user details
import org.springframework.security.web.DefaultRedirectStrategy; // Handles redirection logic
import org.springframework.security.web.authentication.AuthenticationSuccessHandler; // Interface for success handling
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Component to handle successful OAuth authentication events.
 * Redirects authenticated users to a specified frontend URL upon successful login.
 */
@Component
public class OAuthAuthenicationSuccessHandler implements AuthenticationSuccessHandler {

    // Constant representing the frontend URL where users will be redirected post-authentication
    private static final String FRONTEND_URL = "http://localhost:5173/home";

    /**
     * Called when authentication is successful.
     *
     * @param request        The HTTP request object
     * @param response       The HTTP response object
     * @param authentication The authentication object containing user details
     * @throws IOException      If an input/output error occurs during redirection
     * @throws ServletException If a servlet error occurs during processing
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Redirect the user to the frontend home URL using DefaultRedirectStrategy
        new DefaultRedirectStrategy().sendRedirect(request, response, FRONTEND_URL);
    }
}
