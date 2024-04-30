package com.example.socialmediabackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequest signUpRequest) {
        String email = signUpRequest.getEmail();
        User existingUser = userService.findByEmail(email);
        if (existingUser != null) {
            return "Forbidden, Account already exists";
        }

        User newUser = new User();
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setName(signUpRequest.getName());
        newUser.setPassword(signUpRequest.getPassword());

        userService.saveUser(newUser);
        return "Account Creation Successful";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        User user = userService.findByEmail(email);
        if (user == null) {
            return "User does not exist";
        }
        if (!user.getPassword().equals(password)) {
            return "Username/Password Incorrect";
        }
        return "Login Successful";
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(@RequestParam int userID) {
        User user = userService.findById(userID);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found");
        }

        List<UserDetailsDTO> userDetailsDTOList = new ArrayList<>();
        for (User user : users) {
            UserDetailsDTO userDetailsDTO = new UserDetailsDTO(user.getName(), user.getId(), user.getEmail());
            userDetailsDTOList.add(userDetailsDTO);
        }

        return ResponseEntity.ok(userDetailsDTOList);
    }

}