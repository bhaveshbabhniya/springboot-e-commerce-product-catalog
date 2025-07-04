package com.springboot.ecommerce_product_catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
	private Long productId;
	private String productName;
	private int quantity;
	private double price;
}
