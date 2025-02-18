package com.example.bitnbuild.security;

import com.example.bitnbuild.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration class for the application.
 * It sets up authentication, authorization, and security-related features.
 */
@Configuration
@EnableWebSecurity // Enables Spring Security for the application
@EnableMethodSecurity // Enables method-level security annotations (e.g., @PreAuthorize)
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService; // Custom implementation to load user details

    @Autowired
    private JWTAuthFilter jwtAuthFilter; // JWT authentication filter for token validation

    @Autowired
    private OAuthAuthenicationSuccessHandler handler; // Handles OAuth authentication success events

    /**
     * Configures the SecurityFilterChain for HTTP security.
     *
     * @param httpSecurity The HttpSecurity object to configure
     * @return Configured SecurityFilterChain
     * @throws Exception In case of configuration errors
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // Disables CSRF protection (not needed for stateless APIs)
                .cors(Customizer.withDefaults()) // Enables CORS with default configuration
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/**", "/rooms/**", "/bookings/**").permitAll() // Allow public access to these endpoints
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sets session management to stateless
                .authenticationProvider(authenticationProvider()) // Registers the custom authentication provider
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // Adds the JWT filter before username-password filter
                .oauth2Login(oauth -> {
                    oauth.loginPage("/login"); // Specifies custom OAuth login page
                    oauth.successHandler(handler); // Specifies the success handler for OAuth authentication
                });
        return httpSecurity.build(); // Builds and returns the configured SecurityFilterChain
    }

    /**
     * Configures the AuthenticationProvider to use a custom user details service and password encoder.
     *
     * @return Configured AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService); // Sets the custom user details service
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder()); // Sets the password encoder
        return daoAuthenticationProvider;
    }

    /**
     * Configures the PasswordEncoder to use BCrypt hashing algorithm.
     *
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the AuthenticationManager for authentication.
     *
     * @param authenticationConfiguration The configuration for authentication
     * @return Configured AuthenticationManager
     * @throws Exception In case of errors during configuration
     */
    @Bean
    //a Bean is simply an object that is managed by the Spring IoC (Inversion of Control) container
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
