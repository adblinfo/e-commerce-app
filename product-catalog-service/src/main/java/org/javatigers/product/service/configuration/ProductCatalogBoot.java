package org.javatigers.product.service.configuration;

import org.javatigers.product.service.document.Product;
import org.javatigers.product.service.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author ad
 *
 */
@EnableMongoRepositories(basePackages = { "org.javatigers.ecommerce.repository" })
@ComponentScan(basePackages = "org.javatigers.ecommerce.rest")
@SpringBootApplication
public class ProductCatalogBoot {

	private static final Logger logger = LoggerFactory.getLogger(ProductCatalogBoot.class);
	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogBoot.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ProductRepository productRepository) {
		return strings -> {
			/*productRepository.findAll()
				.stream()
				.forEach(product -> productRepository.delete(product));*/
			productRepository.save(Product.builder()
					.currency("INR")
					.description("DJI Phantom 3 Standard Quadcopter Drone with 2.7K HD Video Camera")
					.image("img/01.jpg")
					.name("DJI Phantom 3 Quadcopter Droe")
					.price(222)
					.build());
			productRepository.findAll()
				.stream()
				.forEach(product -> logger.info("{}", product));
		};
	}

	@Bean
	public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator(ObjectMapper objectMapper) {
		Jackson2RepositoryPopulatorFactoryBean populator = new Jackson2RepositoryPopulatorFactoryBean();
		logger.debug("Populate product collections.");
		// Set a custom ObjectMapper if Jackson customization is needed
		populator.setMapper(objectMapper);
		populator.setResources(new Resource[] { new ClassPathResource("/scripts/products.json") });
		return populator;
	}

}
