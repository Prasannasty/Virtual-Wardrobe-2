package com.example.bitnbuild.Controller;


import com.example.bitnbuild.dto.LoginRequest;
import com.example.bitnbuild.dto.Response;
import com.example.bitnbuild.entity.User;
import com.example.bitnbuild.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// The @RestController annotation is used to define a RESTful web service.
// It is a convenience annotation that combines @Controller and @ResponseBody,
// meaning that the methods in this controller will return data directly as JSON or other response bodies.
@RequestMapping("/auth")
// The @RequestMapping("/auth") annotation specifies the base URI for this controller.
// All HTTP requests starting with "/auth" will be mapped to this controller.
// For example, "/auth/register" will map to the register method.
public class AuthController {

    @Autowired
    // The @Autowired annotation is used to automatically inject dependencies into this class.
    // Here, it is used to inject an instance of the UserService class into the controller.
    private UserService userService;

    @PostMapping("/register")
    // The @PostMapping("/register") annotation maps HTTP POST requests for "/auth/register"
    // to the register method. This is typically used for creating or registering a new user.
    public ResponseEntity<Response> register(@RequestBody User user) {
        // The @RequestBody annotation tells Spring to map the incoming HTTP request body
        // to the User object. The client will send the user data in the body of the request.
        System.out.println(user);  // This will print the user data for debugging purposes (it may not be needed in production).

        // The userService is called to handle the registration logic,
        // and a Response object is returned, which will include status code, message, and other details.
        Response response = userService.register(user);

        // The ResponseEntity is used to build the HTTP response.
        // The status code from the response is set, and the response body is the Response object returned by the service.
        return ResponseEntity.status(response.getStatuscode()).body(response);
    }

    @PostMapping("/login")
    // The @PostMapping("/login") annotation maps HTTP POST requests for "/auth/login"
    // to the login method. This is typically used for authenticating a user.
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) {
        // The @RequestBody annotation maps the incoming HTTP request body to a LoginRequest object.
        // This request typically contains user credentials like email and password.

        // The userService is called to handle the login logic,
        // and a Response object is returned, which will contain the authentication token and other login information.
        Response response = userService.login(loginRequest);

        // The ResponseEntity is used to construct the HTTP response with the status code and response body.
        return ResponseEntity.status(response.getStatuscode()).body(response);
    }
}