package com.kevharv.enterprise_users.Group;

public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException(Long id) {
        super("Could not find group " + id);
    }
}
