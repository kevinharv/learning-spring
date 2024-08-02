package com.kevharv.demo;

import java.util.ArrayList;

public class Group {
    private String name;
    private ArrayList<User> members;

    public Group() {}

    public Group(String name, ArrayList<User> members) {
        this.name = name;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addUser(User user) {
        members.add(user);
    }

    public void deleteUser(User user) {
        members.remove(user);
    }
}
