package com.kevharv.java.rest_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    
    @Bean
    CommandLineRunner initDB(EmployeeRepository repository) {
        return args -> {
            log.info("Preloading" + repository.save(new Employee("Foo", "Bar", "Useless Employee")));
            log.info("Preloading" + repository.save(new Employee("Fizz", "Buzz", "Useful Employee")));
        };
    }
}
