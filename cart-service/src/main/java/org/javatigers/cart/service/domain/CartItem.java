package org.javatigers.cart.service.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author ad
 *
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8051572627810607032L;
	private String id;
	private String name;
	private float price;
	private String currency;
}
