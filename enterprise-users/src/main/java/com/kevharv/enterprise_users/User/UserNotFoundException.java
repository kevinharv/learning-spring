package com.kevharv.enterprise_users.User;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long Id) {
        super("Unable to locate user " + Id);
    }
}
