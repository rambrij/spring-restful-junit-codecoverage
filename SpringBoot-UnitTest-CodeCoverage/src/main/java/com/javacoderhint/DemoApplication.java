package com.javacoderhint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.javacoderhint.model.Employee;
import com.javacoderhint.repository.EmpRepository;

@SpringBootApplication
public class DemoApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner setup(EmpRepository empRepository) {
		return (args) -> {
			empRepository.save(new Employee("Mike", 34));
			empRepository.save(new Employee("Andrew", 45));
			empRepository.save(new Employee("John", 23));
			empRepository.save(new Employee("Raj", 54));
			logger.info("The sample data has been generated");
		};
	}
}
