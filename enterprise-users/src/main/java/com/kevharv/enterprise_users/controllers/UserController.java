package com.kevharv.enterprise_users.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kevharv.enterprise_users.exceptions.UserNotFoundException;
import com.kevharv.enterprise_users.models.Group;
import com.kevharv.enterprise_users.models.User;
import com.kevharv.enterprise_users.repos.GroupRepository;
import com.kevharv.enterprise_users.repos.UserRepository;

@RestController
public class UserController {
    @Autowired
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    UserController(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @GetMapping("/users")
    List<User> all() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

    @GetMapping("/users/{id}")
    User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/users")
    User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    User replaceUser(@PathVariable Long id, @RequestBody User user) {
        // Ensure user exists - throw otherwise
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        // (Lazy) replace existing user with specified user
        return userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // Group operations
    @PostMapping("/users/{id}/addGroup")
    ResponseEntity<?> addGroup(@PathVariable Long id, @RequestParam Long groupID) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        Group group = groupRepository.findById(groupID).orElse(null);
        if (group == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (user.getGroups().contains(group)) {
            // User already in group
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        user.addGroup(group);
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @PostMapping("/users/{id}/removeGroup")
    ResponseEntity<?> removeGroup(@PathVariable Long id, @RequestParam Long groupID) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        Group group = groupRepository.findById(groupID).orElse(null);
        if (group == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.removeGroup(group);
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }
}
