package org.javatigers.ecommerce.configuration;

import org.javatigers.ecommerce.domain.Cart;
import org.javatigers.ecommerce.domain.CartItem;
import org.javatigers.ecommerce.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author ad
 *
 */
@SpringBootApplication
@RequiredArgsConstructor
public class CartConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(CartConfiguration.class);
	

	public static void main(String[] args) {
		SpringApplication.run(CartConfiguration.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CartRepository cartRepository) {
		return strings -> {
			logger.info("Test");
			Cart cart = cartRepository.addToCart(null,
					CartItem.builder()
						.currency("INR")
						.name("new product item")
						.price(23.0f)
						.build());
			logger.info("Cart : {}.", cartRepository.findById(cart.getId()));
		};
	}

}

@Configuration
@EnableCaching
@EnableRedisRepositories
@ComponentScan(basePackages = { "org.javatigers.ecommerce.repository", "org.javatigers.ecommerce.rest" })
class RedisCacheConfiguration extends CachingConfigurerSupport {
	private static final Logger logger = LoggerFactory.getLogger(CartConfiguration.class);

	@Value("${redis.server.hostname:127.0.0.1}")
	protected String serverHostname;
	@Value("${redis.server.port:6379}")
	protected int serverPort;
	@Value("${redis.cacheManager.expiration: 1800}")
	protected int cacheManagerExpiration;

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		logger.debug("Redis connection settings. Hostname: {}, port: {}", serverHostname, serverPort);
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();

		// Defaults
		redisConnectionFactory.setHostName(serverHostname);
		redisConnectionFactory.setPort(serverPort);
		return redisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, Cart> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Cart> redisTemplate = new RedisTemplate<String, Cart>();
		redisTemplate.setConnectionFactory(connectionFactory);
		return redisTemplate;
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate<String, Cart> redisTemplate) {
		logger.debug("RedisCacheManager is the cache manager.");
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		// Number of seconds before expiration. Defaults to unlimited (0)
		cacheManager.setDefaultExpiration(cacheManagerExpiration);
		return cacheManager;
	}

}
