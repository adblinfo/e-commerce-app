package org.javatigers.product.service.rest;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.javatigers.product.service.document.Product;
import org.javatigers.product.service.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * @author ad
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class ProductRSV1 extends ResponseEntityExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductRSV1.class);
	private final ProductRepository productRepository;
	
	@Autowired
	public ProductRSV1 (ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@RequestMapping("ping")
    public String ping() {
        return "<h1>Welcome to Product Catalog API!</h1>";
    }
	
	@RequestMapping(value = "products/recomendations", method = GET)
	public @ResponseBody List<Product> productRecomendations () {
		logger.debug("Product recomendations.");
		return productRepository.findAll();
	}
	
	@RequestMapping(value = "products/{_id}", method = GET)
	public @ResponseBody Product readProduct (@PathVariable("_id") String id) {
		logger.debug("Product by id : {}.", id);
		return productRepository.findById(id);
	}
	
	@RequestMapping(value = "products", method = POST)
	public @ResponseBody Product createProduct (@RequestBody Product product) {
		logger.debug("Create Product : {}.", product);
		product.set_class(Product.class.getName());
		return productRepository.insert(product);
	}
	
	@RequestMapping(value = "products", method = PUT)
	public @ResponseBody Product updateProduct (@RequestBody Product product) {
		logger.debug("Update Product : {}.", product);
		product.set_class(Product.class.getName());
		return productRepository.save(product);
	}
	
	@RequestMapping(value = "products/{_id}", method = DELETE)
	public void deleteProduct (@PathVariable("_id") String id) {
		logger.debug("Delete product by id : {}", id);
		productRepository.delete(productRepository.findById(id));
	}
	
	@ExceptionHandler(Exception.class)
    void handleExceptions(HttpServletResponse response) throws IOException {
        response.sendError(INTERNAL_SERVER_ERROR.value(), "Internal server error. We will be addressing this issue soon.");
    }
}
