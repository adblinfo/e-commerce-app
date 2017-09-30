package org.javatigers.cart.service.domain;

import java.io.Serializable;
import java.util.List;


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
@ToString(callSuper = false, exclude = {"items"})
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7958015374248121495L;
	private String id;
	private List<CartItem> items;
	private float total;
	private String currency;
}
