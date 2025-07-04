package com.foxdev.project.findJobProject.service.impl;

import com.foxdev.project.findJobProject.domain.User;
import com.foxdev.project.findJobProject.exception.IdInvalidException;
import com.foxdev.project.findJobProject.exception.InvalidRequestException;
import com.foxdev.project.findJobProject.exception.UserAlreadyExistedException;
import com.foxdev.project.findJobProject.exception.UserNotFoundException;
import com.foxdev.project.findJobProject.repository.UserRepository;
import com.foxdev.project.findJobProject.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if (user == null || user.getId() != null) throw new InvalidRequestException("Invalid data provided");
        if (this.userRepository.existsByEmail(user.getEmail())) throw new UserAlreadyExistedException("Email " +user.getEmail() + " already exists");

        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        if (id == null || id <= 0) throw new IdInvalidException("Id is invalid");

        Optional<User> optionalUser = this.userRepository.findById(id);

        return optionalUser.orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> optionalUser = this.userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User currentUser = optionalUser.get();
            currentUser.setEmail(user.getEmail());
            currentUser.setName(user.getName());
            currentUser.setPassword(user.getPassword());
            return this.userRepository.save(currentUser);
        }
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (id == null || id <= 0) throw new IdInvalidException("Id is invalid");

        Optional<User> optionalUser = this.userRepository.findById(id);

        if (optionalUser.isPresent()) {
            this.userRepository.delete(optionalUser.get());
            return true;
        }

        throw new UserNotFoundException("User not found");
    }
}
