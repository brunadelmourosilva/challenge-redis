package com.brunadelmouro.redisproject.controller;

import com.brunadelmouro.redisproject.model.User;
import com.brunadelmouro.redisproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        userService.saveUser(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }
}
