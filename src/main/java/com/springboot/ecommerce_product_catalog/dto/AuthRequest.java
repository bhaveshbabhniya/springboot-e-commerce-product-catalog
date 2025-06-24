package com.springboot.ecommerce_product_catalog.dto;

import lombok.Data;

@Data
public class AuthRequest {
	private String username;
	private String password;
}
