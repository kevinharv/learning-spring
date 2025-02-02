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
import org.springframework.web.bind.annotation.RestController;

import com.kevharv.enterprise_users.Utilities;
import com.kevharv.enterprise_users.exceptions.GroupNotFoundException;
import com.kevharv.enterprise_users.models.Group;
import com.kevharv.enterprise_users.repos.GroupRepository;

@RestController
public class GroupController {
    @Autowired
    private final GroupRepository groupRepository;

    GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping("/groups")
    public List<Group> getGroups() {
        List<Group> groups = new ArrayList<>();
        groupRepository.findAll().forEach(group -> groups.add(group));
        return groups;
    }

    @GetMapping("/groups/{id}")
    public Group getGroupByID(@PathVariable Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
    }

    @PostMapping("/groups")
    public Group createGroup(@RequestBody Group group) {
        return groupRepository.save(group);
    }

    @PutMapping("/groups/{id}")
    public Group updateGroup(@RequestBody Group group, @PathVariable Long id) {
        Group existingGroup = groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
        Utilities.copyNonNullProperties(group, existingGroup);
        return groupRepository.save(existingGroup);
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
        groupRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
