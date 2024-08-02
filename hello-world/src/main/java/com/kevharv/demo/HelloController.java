package com.kevharv.demo;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/")
    public String index() {
        return "Foo Bar Hello There";
    }

    @GetMapping("/user")
    public User getUser() {
        User user = new User("Temoc", 45);

        return user;
    }

    @GetMapping("/groups")
    public Group getGroup() {
        Group group = new Group("Test Group", new ArrayList<User>());
        User user = new User("Temoc Enarc", 45);
        group.addUser(user);

        return group;
    }
}