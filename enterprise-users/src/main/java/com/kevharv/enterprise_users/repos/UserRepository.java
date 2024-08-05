package com.kevharv.enterprise_users.repos;

import org.springframework.data.repository.CrudRepository;

import com.kevharv.enterprise_users.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
