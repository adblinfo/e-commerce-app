package org.javatigers.cart.service.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.javatigers.cart.service.domain.Cart;
import org.javatigers.cart.service.domain.CartItem;
import org.javatigers.cart.service.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class CartRSV1 {
	private static final Logger logger = LoggerFactory.getLogger(CartRSV1.class);

    private final CartRepository cartRepository;
    
    @Autowired
    public CartRSV1 (CartRepository cartRepository) {
    	this.cartRepository = cartRepository;
    }
	@RequestMapping(value = "ping", method = GET)
	public String ping () {
		return "<h1>Welcome to Cart API!</h1>";
	}
	
	@RequestMapping(value = "cart/{id}", method = GET)
    public Cart cart(@PathVariable("id") String id) {
        logger.debug("Received request for cart by id: {}", id);
        Cart cart = cartRepository.findById(id);
        logger.debug("Cart: {}", cart);
        return cart;
    }

    @RequestMapping(value = "cart/{id}", method = PUT)
    public Cart cart(@PathVariable("id") String id, @RequestBody CartItem cartItem) {
        logger.debug("Received request to add item to cart by id: {}", id);
        Cart cart = cartRepository.addToCart(id, cartItem);
        logger.debug("Cart: {}", cart);
        return cart;
    }

    @RequestMapping(value = "cart", method = POST)
    public Cart cart(@RequestBody CartItem cartItem) {
        logger.debug("Received request to add item to cart without id.");
        Cart cart = cartRepository.addToCart(null, cartItem);
        logger.debug("Cart: {}", cart);
        return cart;
    }

    @ExceptionHandler(Exception.class)
    void handleExceptions(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error. We will be addressing this issue soon.");
    }
}
