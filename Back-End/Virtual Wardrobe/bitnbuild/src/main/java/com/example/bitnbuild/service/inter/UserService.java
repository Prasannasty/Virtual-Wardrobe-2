package com.example.bitnbuild.service.inter;

import com.example.bitnbuild.dto.LoginRequest;
import com.example.bitnbuild.dto.Response;
import com.example.bitnbuild.entity.User;

public interface UserService {

    /**
     * Authenticates the user using the provided login credentials.
     *
     * @param loginRequest contains the login information (email and password).
     * @return Response containing login status and authentication token.
     */
    Response login(LoginRequest loginRequest);

    /**
     * Registers a new user into the system.
     *
     * @param user The user object containing registration details (email, password, etc.).
     * @return Response containing registration status and any relevant data.
     */
    Response register(User user);

    /**
     * Retrieves all users from the system.
     *
     * @return Response containing a list of all users in the system.
     */
    Response getAllUsers();

    /**
     * Deletes a user based on the provided user ID.
     *
     * @param userid The ID of the user to be deleted.
     * @return Response indicating whether the deletion was successful or not.
     */
    Response deleteUser(String userid);

    /**
     * Retrieves user information based on the provided user ID.
     *
     * @param userid The ID of the user to fetch.
     * @return Response containing user details or an error message if not found.
     */
    Response getUserById(String userid);

    /**
     * Retrieves the current user's information based on their email.
     *
     * @param email The email of the current user.
     * @return Response containing the user's data.
     */
    Response getMyInfo(String email);

    /**
     * Fetches the currently authenticated user.
     *
     * @return The currently authenticated User object.
     */
    User getCurrentUser();

    /**
     * Finds a user by their email.
     *
     * @param username The email address of the user.
     * @return The User object associated with the email.
     */
    User findByEmail(String username);

    /**
     * Finds a user by their ID.
     *
     * @param uploaderId The ID of the user.
     * @return The User object associated with the ID.
     */
    User findById(Long uploaderId);
}
