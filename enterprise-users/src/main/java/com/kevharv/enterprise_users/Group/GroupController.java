package com.kevharv.enterprise_users.Group;

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
        Group dbGroup = groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
        dbGroup.setDescription(group.getDescription());
        dbGroup.setName(group.getName());
        return groupRepository.save(dbGroup);
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
        groupRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
