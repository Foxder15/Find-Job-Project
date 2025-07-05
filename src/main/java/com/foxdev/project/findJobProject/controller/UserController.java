package com.foxdev.project.findJobProject.controller;

import com.foxdev.project.findJobProject.domain.User;
import com.foxdev.project.findJobProject.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users =  this.userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User currentUser = this.userService.getUserById(id);
        return ResponseEntity.ok().body(currentUser);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = this.userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    //@Todo: update method will be complete later.
    @PutMapping("{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return this.userService.updateUser(id, user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return this.userService.deleteUser(id) ?  ResponseEntity.status(HttpStatus.NO_CONTENT).body("success") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
