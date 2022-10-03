package com.example.accessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class AccessingDataJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository, OrderRepository orderRepository, TagRepository tagRepo) {
		return (args) -> {
			Tag tag1 = new Tag("green");
			Tag tag2 = new Tag("transparent");
			Tag tag3 = new Tag("immutable");
			tagRepo.save(tag1);
			tagRepo.save(tag2);
			tagRepo.save(tag3);

			log.info("-------------------------------");
			tagRepo.findAll().forEach(t -> log.info(t.toString()));

			Orders order1 = new Orders("Food");
			Orders order2 = new Orders("Drink");
			orderRepository.save(order1);
			orderRepository.save(order2);

			log.info("-------------------------------");
			orderRepository.findAll().forEach(o -> log.info(o.toString()));

			// save a few customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer",
					Collections.emptyList(), tag1));
			repository.save(new Customer("David", "Palmer",
					Collections.emptyList(), tag2));
			repository.save(new Customer("Michelle", "Dessler",
					Arrays.asList(order1, order2), tag2));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> log.info(bauer.toString()));
			log.info("");
		};
	}
}
