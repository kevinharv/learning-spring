package com.kevharv.enterprise_users;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kevharv.enterprise_users.User.UserRepository;
import com.kevharv.enterprise_users.Group.GroupRepository;

@SpringBootApplication
public class EnterpriseUsersApplication {

	private static final Logger log = LoggerFactory.getLogger(EnterpriseUsersApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EnterpriseUsersApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository, GroupRepository groupRepository) {
		return (args) -> {
			log.info("=== FINDING ALL USERS ===");
			userRepository.findAll().forEach(user -> {
				log.info(user.getDisplayName());
			});
		};
	}

}
