package com.kevharv.enterprise_users;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kevharv.enterprise_users.User.User;
import com.kevharv.enterprise_users.User.UserRepository;

@SpringBootApplication
public class EnterpriseUsersApplication {

	private static final Logger log = LoggerFactory.getLogger(EnterpriseUsersApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EnterpriseUsersApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository) {
		return (args) -> {
			userRepository.save(new User("1"));
			userRepository.save(new User("2"));
			userRepository.save(new User("3"));
			userRepository.save(new User("4"));
			userRepository.save(new User("5"));


			log.info("=== FINDING ALL USERS ===");
			userRepository.findAll().forEach(user -> {
				log.info(user.toString());
			});
		};
	}

}
