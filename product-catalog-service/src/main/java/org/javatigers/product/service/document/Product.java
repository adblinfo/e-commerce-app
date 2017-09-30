package org.javatigers.product.service.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@JsonIdentityInfo(generator=IntSequenceGenerator.class, property="_id")
@Document
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	private String _id;
	private String _class;
    private String name;
    private String description;
    private float price;
    private String currency;
    private String image;
    private String url;
}
