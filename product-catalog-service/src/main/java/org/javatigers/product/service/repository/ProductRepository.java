package org.javatigers.product.service.repository;

import org.javatigers.product.service.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * 
 * @author ad
 *
 */
public interface ProductRepository extends MongoRepository<Product, Long> {

	@Query("{ '_id' : ?0 }")
	Product findById(String id);

}
