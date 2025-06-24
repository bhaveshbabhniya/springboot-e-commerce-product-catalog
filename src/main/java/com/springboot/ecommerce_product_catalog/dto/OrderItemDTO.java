package com.springboot.ecommerce_product_catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

	private Long id;
	private String productName;
	private double price;
	private int quantity;
}
