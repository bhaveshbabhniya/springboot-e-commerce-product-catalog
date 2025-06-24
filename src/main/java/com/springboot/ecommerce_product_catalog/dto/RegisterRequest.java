package com.springboot.ecommerce_product_catalog.dto;

import com.springboot.ecommerce_product_catalog.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
	private String username;
	private String password;
	private Role role;
}
