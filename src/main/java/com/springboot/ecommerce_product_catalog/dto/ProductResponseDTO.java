package com.springboot.ecommerce_product_catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

	private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String categoryName;
    private String sellerUsername;
}
