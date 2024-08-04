package com.kevharv.enterprise_users;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kevharv.enterprise_users.User.UserRepository;
import com.kevharv.enterprise_users.Group.Group;
import com.kevharv.enterprise_users.Group.GroupRepository;
import com.kevharv.enterprise_users.User.User;

@SpringBootApplication
public class EnterpriseUsersApplication {

	private static final Logger log = LoggerFactory.getLogger(EnterpriseUsersApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EnterpriseUsersApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository, GroupRepository groupRepository) {
		return (args) -> {

			Group adminGroup = new Group("Administrative Users");
			Group dbgroup = groupRepository.save(adminGroup);

			User adminUser = new User("123");
			User dbUser = userRepository.save(adminUser);

			dbUser.addGroup(dbgroup);
			userRepository.save(dbUser);

			log.info("=== FINDING ALL USERS ===");
			userRepository.findAll().forEach(user -> {
				log.info(user.toString());
			});
		};
	}

}
