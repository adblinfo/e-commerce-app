package org.javatigers.cart.service.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.javatigers.cart.service.domain.Cart;
import org.javatigers.cart.service.domain.CartItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author ad
 *
 */
@Repository
public class CartRepositoryImpl implements CartRepository {

	private static final Logger logger = LoggerFactory.getLogger(CartRepositoryImpl.class);
	
	@Autowired
	private final RedisTemplate<String, Cart> redisTemplate;
	
	public CartRepositoryImpl (RedisTemplate<String, Cart> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	@Override
	public Cart findById(String id) {
		return redisTemplate.opsForValue().get(id);
	}

	@Override
	public Cart addToCart(String id, CartItem cartItem) {
        Cart cart = null;
        logger.debug("cart id: {}", id);
        // If cart does not exist.
        if ((id == null) || (id.equalsIgnoreCase(""))) {
        	logger.debug("Missing id, creating new cart.");
            cart = createCart(UUID.randomUUID().toString(), cartItem);
        } else {
            cart = findById(id);
            logger.debug("Retrieve existing cart by id: {}, cart: {}", id, cart);
            if (cart == null) {
                cart = createCart(id, cartItem);
            } else {
                cart.getItems().add(cartItem);
            }
        }

        //cart.getItems().stream().mapToDouble(CartItem::getPrice).sum()
        cart.setTotal(cart.getItems().stream().map(CartItem::getPrice).reduce(Float::sum).orElseGet(() -> new Float(0)));
        logger.debug("cart: " + cart);
        redisTemplate.opsForValue().set(cart.getId(), cart);
        return cart;
	}

	private Cart createCart(String id, CartItem cartItem) {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        cartItems.add(cartItem);
        Cart cart = new Cart(id, cartItems, cartItem.getPrice(), cartItem.getCurrency());
        // If cart by id exist, then add to it.
        return cart;
    }
}
