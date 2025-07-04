package com.foxdev.project.findJobProject.service;

import com.foxdev.project.findJobProject.domain.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    boolean deleteUser(Long id);
}
