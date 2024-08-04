package com.kevharv.enterprise_users.Group;

public class GroupNotFoundException extends RuntimeException {
    GroupNotFoundException(Long id) {
        super("Could not find group " + id);
    }
}
