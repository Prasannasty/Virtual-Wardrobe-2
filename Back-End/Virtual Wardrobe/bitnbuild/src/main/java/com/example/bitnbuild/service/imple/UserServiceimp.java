package com.example.bitnbuild.service.imple;

import com.example.bitnbuild.dto.LoginRequest;
import com.example.bitnbuild.dto.Response;
import com.example.bitnbuild.dto.UserDto;
import com.example.bitnbuild.entity.User;
import com.example.bitnbuild.exception.OurException;
import com.example.bitnbuild.repo.UserRepository;
import com.example.bitnbuild.service.inter.UserService;
import com.example.bitnbuild.utils.JWTUtils;
import com.example.bitnbuild.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for handling user-related operations such as login, registration, and user management.
 * This class implements the UserService interface.
 */
@Service
public class UserServiceimp implements UserService {

    @Autowired
    private UserRepository userRepository; // Repository to interact with user data in the database

    @Autowired
    private PasswordEncoder passwordEncoder; // To encode user passwords

    @Autowired
    private JWTUtils jwtUtils; // Utility for handling JWT token generation and validation

    @Autowired
    private AuthenticationManager authenticationManager; // Authentication manager to handle authentication

    /**
     * Authenticates the user based on email and password and generates a JWT token if successful.
     *
     * @param loginRequest The login request containing user credentials (email and password)
     * @return Response A response object containing the authentication status and JWT token
     */
    @Override
    public Response login(LoginRequest loginRequest) {
        Response response = new Response();
        try {
            // Attempt authentication using the provided credentials (email and password)
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            // If authentication is successful, fetch user details
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new OurException("User not found"));

            // Generate JWT token for the authenticated user
            var token = jwtUtils.generateToken(user);

            // Set the response with status code and other relevant details
            response.setStatuscode(200);
            response.setToken(token);
            response.setRole(user.getRole());
            response.setExpirationTime("7 Days");
            response.setMessage("Successful");
        } catch (OurException e) {
            response.setStatuscode(400);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatuscode(500);
            response.setMessage("Error occurred during user login: " + e.getMessage());
        }
        return response;
    }

    /**
     * Registers a new user. If the role is not provided, it defaults to "USER".
     * Encrypts the user's password before saving it in the database.
     *
     * @param user The user to be registered
     * @return Response A response object containing the status of the registration
     */
    @Override
    public Response register(User user) {
        Response response = new Response();
        try {
            // Set default role if not provided
            if (user.getRole() == null || user.getRole().isBlank()) {
                user.setRole("USER");
            }

            // Check if the email already exists in the database
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new OurException(user.getEmail() + " Already exists");
            }

            // Encrypt the user's password
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // Save the user in the database
            User savedUser = userRepository.save(user);

            // Convert the saved user entity to a DTO for response
            UserDto userDto = Utils.mapUserEntityToUserDto(savedUser);

            // Set the response with the saved user data
            response.setStatuscode(200);
            response.setUser(userDto);
        } catch (OurException e) {
            response.setStatuscode(400);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatuscode(500);
            response.setMessage("Error occurred during user registration: " + e.getMessage());
        }
        return response;
    }

    /**
     * Fetches all users from the database and returns them as a list of DTOs.
     *
     * @return Response A response object containing the list of users
     */
    @Override
    public Response getAllUsers() {
        Response response = new Response();
        try {
            // Retrieve all users from the repository
            List<User> userList = userRepository.findAll();

            // Convert the list of user entities to a list of DTOs
            List<UserDto> userDtoList = Utils.mapUserListEntityToUserListDTO(userList);

            // Set the response with the user list
            response.setStatuscode(200);
            response.setMessage("Successful");
            response.setUserlist(userDtoList);
        } catch (Exception e) {
            response.setStatuscode(500);
            response.setMessage("Error occurred while fetching users: " + e.getMessage());
        }
        return response;
    }

    /**
     * Fetches the current authenticated user from the SecurityContext.
     *
     * @return User The current authenticated user
     */
    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found")); // Handle user not found case
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userid The ID of the user to delete
     * @return Response A response object indicating the success or failure of the deletion
     */
    @Override
    public Response deleteUser(String userid) {
        Response response = new Response();
        try {
            // Check if the user exists before attempting deletion
            userRepository.findById(Long.valueOf(userid)).orElseThrow(() -> new OurException("User not found"));
            userRepository.deleteById(Long.valueOf(userid));

            // Set the response with success message
            response.setStatuscode(200);
            response.setMessage("Successful");
        } catch (OurException e) {
            response.setStatuscode(400);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatuscode(500);
            response.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return response;
    }

    /**
     * Finds a user by email and throws an exception if the user does not exist.
     *
     * @param email The email of the user to find
     * @return User The user with the specified email
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    /**
     * Finds a user by ID and throws an exception if the user does not exist.
     *
     * @param id The ID of the user to find
     * @return User The user with the specified ID
     */
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
    }

    /**
     * Fetches a user by their ID and returns the user data as a DTO.
     *
     * @param userid The ID of the user to fetch
     * @return Response A response object containing the user data
     */
    @Override
    public Response getUserById(String userid) {
        Response response = new Response();
        try {
            User user = userRepository.findById(Long.valueOf(userid)).orElseThrow(() -> new OurException("User not found"));
            UserDto userDto = Utils.mapUserEntityToUserDto(user);

            // Set the response with the user data
            response.setStatuscode(200);
            response.setMessage("Successful");
            response.setUser(userDto);
        } catch (OurException e) {
            response.setStatuscode(400);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatuscode(500);
            response.setMessage("Error occurred while fetching user: " + e.getMessage());
        }
        return response;
    }

    /**
     * Fetches user information by email.
     *
     * @param email The email of the user
     * @return Response A response object containing the user data
     */
    @Override
    public Response getMyInfo(String email) {
        Response response = new Response();
        try {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new OurException("User not found"));

            // Set the response indicating successful retrieval of user info
            response.setStatuscode(200);
            response.setMessage("Successful");
        } catch (OurException e) {
            response.setStatuscode(400);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatuscode(500);
            response.setMessage("Error occurred while fetching user info: " + e.getMessage());
        }
        return response;
    }
}
