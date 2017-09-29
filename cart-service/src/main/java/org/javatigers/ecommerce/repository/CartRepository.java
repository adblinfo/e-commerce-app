package org.javatigers.ecommerce.repository;

import org.javatigers.ecommerce.domain.Cart;
import org.javatigers.ecommerce.domain.CartItem;

/**
 * 
 * @author ad
 *
 */
public interface CartRepository {
	Cart findById(String id);

    Cart addToCart(String id, CartItem cartItem);
}
