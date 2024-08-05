package io.rcrr.springboot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.rcrr.springboot.domain.Category;
import io.rcrr.springboot.repositories.CategoryRepository;

@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner{
	@Autowired
	CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	// Creates objects in runtime to be save in the database
	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Technology");
		Category cat2 = new Category(null, "Office");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
	}

}
