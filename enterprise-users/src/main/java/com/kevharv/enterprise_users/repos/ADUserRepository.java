package com.kevharv.enterprise_users.repos;

import org.springframework.data.repository.CrudRepository;

import com.kevharv.enterprise_users.models.ADUser;

public interface ADUserRepository extends CrudRepository<ADUser, Long> {

}
