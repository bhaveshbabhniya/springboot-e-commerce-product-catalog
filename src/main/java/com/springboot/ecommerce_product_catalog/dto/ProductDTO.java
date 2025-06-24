package com.springboot.ecommerce_product_catalog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
	private String name;
	private String description;
	private double price;
	private int stock;
	private Long categoryId;
}
