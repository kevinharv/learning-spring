package com.kevharv.enterprise_users.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long Id) {
        super("Unable to locate user " + Id);
    }
}
