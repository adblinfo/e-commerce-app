package org.javatigers.cart.service.repository;

import org.javatigers.cart.service.domain.Cart;
import org.javatigers.cart.service.domain.CartItem;

/**
 * 
 * @author ad
 *
 */
public interface CartRepository {
	Cart findById(String id);

    Cart addToCart(String id, CartItem cartItem);
}
